package alex.example.movies.ui.screens.maincontent

import alex.example.movies.ui.viewmodels.maincontent.PeopleFragmentViewModel
import alex.example.movies.databinding.FragmentPeopleBinding
import alex.example.movies.ui.adapters.PeopleAdapter
import alex.example.movies.ui.adapters.comparator.PeopleComparator
import alex.example.movies.ui.adapters.pagingloadstate.PagingLoadingStateAdapter
import alex.example.movies.utils.BaseFragment
import android.os.Bundle
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
    private lateinit var searchPeopleAdapter: PeopleAdapter

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
            peopleRv.layoutManager = layoutManager
            peopleRv.adapter = popularPeopleAdapter.withLoadStateFooter(
                footerLoadingAdapter
            )

            // Setup Search RV Adapter
            val searchLayoutManager = GridLayoutManager(context, 2)
            searchPeopleAdapter = PeopleAdapter(PeopleComparator).apply {
                viewLifecycleOwner.lifecycleScope.launch {
                    loadStateFlow.collectLatest {
                        searchLoadErrorLayout.isVisible = it.refresh is LoadState.Error
                        searchShimmer(it.refresh is LoadState.Loading)
                    }
                }
            }

            val searchFooterLoadingAdapter = PagingLoadingStateAdapter(searchPeopleAdapter::retry)
            searchLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int =
                    if (position == searchPeopleAdapter.itemCount && searchFooterLoadingAdapter.itemCount > 0) 2 else 1
            }
            searchViewRv.layoutManager = searchLayoutManager
            searchViewRv.adapter = searchPeopleAdapter.withLoadStateFooter(
                searchFooterLoadingAdapter
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

            // Collect search people
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.searchPeopleState.collectLatest {
                    it?.let {
                        searchPeopleAdapter.submitData(it)
                    }
                }
            }

            // Retry Button
            loadErrorView.retryBtn.setOnClickListener { viewModel.fetchPopularPeople() }
            searchLoadErrorView.retryBtn.setOnClickListener { viewModel.fetchSearchPeople() }

            viewModel.fetchPopularPeople()

            searchView.editText.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                    viewModel.setAndFetchSearchPeople(searchView.editText.text.toString())
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }


        }
    }

    private fun shimmer(showShimmer: Boolean) = with(binding) {
        shimmerLayout.isVisible = showShimmer
        peopleRv.isVisible = !showShimmer
        shimmerLayout.apply {
            if (showShimmer) startShimmer() else stopShimmer()
        }
    }

    private fun searchShimmer(showShimmer: Boolean) = with(binding) {
        searchShimmerLayout.isVisible = showShimmer
        searchViewRv.isVisible = !showShimmer
        searchShimmerLayout.apply {
            if (showShimmer) startShimmer() else stopShimmer()
        }
    }

}
