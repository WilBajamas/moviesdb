package alex.example.movies.ui.screens.maincontent

import alex.example.movies.R
import alex.example.movies.ui.viewmodels.maincontent.SharedMoviesFilterFragmentViewModel
import alex.example.movies.databinding.FragmentMoviesBinding
import alex.example.movies.ui.adapters.MoviesAdapter
import alex.example.movies.ui.screens.filter.FilterFragment
import alex.example.movies.utils.BaseFragment
import alex.example.movies.utils.Resource
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
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
            moviesRv.layoutManager = GridLayoutManager(context, 2)
            moviesAdapter = MoviesAdapter()
            moviesRv.adapter = moviesAdapter

            swipeRefreshLayout.setOnRefreshListener {
                viewModel.callMoviesApi()
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
        }

        viewModel.callMoviesApi()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.moviesState.collectLatest {
                when (it) {
                    is Resource.Error -> binding.shimmerLayout.stopShimmer()
                    is Resource.Loading -> shimmer(true)
                    is Resource.Success -> {
                        it.data?.results?.let { movies ->
                            shimmer(false)
                            moviesAdapter.updateItems(
                                movies
                            )
                            binding.swipeRefreshLayout.isRefreshing = false
                        }
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

    private fun shimmer(showShimmer: Boolean) {
        binding.shimmerLayout.isVisible = showShimmer
        binding.moviesRv.isVisible = !showShimmer
        binding.shimmerLayout.apply {
            if (showShimmer) startShimmer() else stopShimmer()
        }
    }
}
