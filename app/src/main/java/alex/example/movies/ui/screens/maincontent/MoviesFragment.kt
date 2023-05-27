package alex.example.movies.ui.screens.maincontent

import alex.example.movies.R
import alex.example.movies.ui.viewmodels.maincontent.SharedMoviesFilterFragmentViewModel
import alex.example.movies.databinding.FragmentMoviesBinding
import alex.example.movies.ui.adapters.MoviesAdapter
import alex.example.movies.ui.adapters.UserComparator
import alex.example.movies.ui.adapters.pagingloadstate.PagingLoadingStateAdapter
import alex.example.movies.ui.screens.filter.FilterFragment
import alex.example.movies.utils.BaseFragment
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding, SharedMoviesFilterFragmentViewModel>(
    FragmentMoviesBinding::inflate, SharedMoviesFilterFragmentViewModel::class.java
) {

    private lateinit var moviesAdapter: MoviesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            // Setup RV Adapter
            val layoutManager = GridLayoutManager(context, 2)
            val footerLoadingAdapter = PagingLoadingStateAdapter()
            moviesAdapter = MoviesAdapter(UserComparator).apply {
                viewLifecycleOwner.lifecycleScope.launch {
                    loadStateFlow.collectLatest {
                        loadErrorLayout.isVisible = it.refresh is LoadState.Error
                        shimmer(it.refresh is LoadState.Loading)
                    }
                }
            }
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int =
                    if (position == moviesAdapter.itemCount && footerLoadingAdapter.itemCount > 0) 2 else 1
            }
            moviesRv.layoutManager = layoutManager
            moviesRv.adapter = moviesAdapter.withLoadStateFooter(
                footerLoadingAdapter
            )

            swipeRefreshLayout.setOnRefreshListener {
                viewModel.fetchMovies()
            }

            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_filter -> {
                        showDialog()
                        true
                    }
                    else -> false
                }
            }

            viewModel.fetchMovies()

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.moviesState.collectLatest {
                    it?.let {
                        swipeRefreshLayout.isRefreshing = false
                        moviesAdapter.submitData(it)
                    }
                }
            }
        }
    }

    private fun showDialog() {
        val fragmentManager = childFragmentManager
        val newFragment = FilterFragment()
        newFragment.show(fragmentManager, "dialog")
    }

    private fun shimmer(showShimmer: Boolean) = with(binding) {
        shimmerLayout.isVisible = showShimmer
        moviesRv.isVisible = !showShimmer
        shimmerLayout.apply {
            if (showShimmer) startShimmer() else stopShimmer()
        }
    }
}
