package alex.example.movies.ui.screens.maincontent

import alex.example.movies.ui.viewmodels.maincontent.PeopleFragmentViewModel
import alex.example.movies.databinding.FragmentPeopleBinding
import alex.example.movies.ui.adapters.PeopleAdapter
import alex.example.movies.ui.adapters.comparator.PeopleComparator
import alex.example.movies.ui.adapters.pagingloadstate.PagingLoadingStateAdapter
import alex.example.movies.utils.BaseFragment
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PeopleFragment : BaseFragment<FragmentPeopleBinding, PeopleFragmentViewModel>(
    FragmentPeopleBinding::inflate, PeopleFragmentViewModel::class.java
) {

    private lateinit var popularPeopleAdapter: PeopleAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

// Setup RV Adapter
            val layoutManager = GridLayoutManager(context, 2)
            popularPeopleAdapter = PeopleAdapter(PeopleComparator).apply {
                viewLifecycleOwner.lifecycleScope.launch {
                    loadStateFlow.collectLatest {
                        loadErrorLayout.isVisible = it.refresh is LoadState.Error
                        swipeRefreshLayout.isVisible = it.refresh !is LoadState.Error
                        shimmer(it.refresh is LoadState.Loading)
                    }
                }
            }

            val footerLoadingAdapter = PagingLoadingStateAdapter(popularPeopleAdapter::retry)
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int =
                    if (position == popularPeopleAdapter.itemCount && footerLoadingAdapter.itemCount > 0) 2 else 1
            }
            moviesRv.layoutManager = layoutManager
            moviesRv.adapter = popularPeopleAdapter.withLoadStateFooter(
                footerLoadingAdapter
            )

            // Swipe refresh
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.fetchPopularPeople()
            }

            // Collect people
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.peopleState.collectLatest {
                    it?.let {
                        swipeRefreshLayout.isRefreshing = false
                        popularPeopleAdapter.submitData(it)
                    }
                }
            }

            // Retry Button
            loadErrorView.retryBtn.setOnClickListener { viewModel.fetchPopularPeople() }

            viewModel.fetchPopularPeople()

            searchView.editText.setOnKeyListener { _, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    Log.i("SearchView: ", "Search Clicked")
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }


        }
    }

    private fun shimmer(showShimmer: Boolean) = with(binding) {
        shimmerLayout.isVisible = showShimmer
        moviesRv.isVisible = !showShimmer
        shimmerLayout.apply {
            if (showShimmer) startShimmer() else stopShimmer()
        }
    }

}
