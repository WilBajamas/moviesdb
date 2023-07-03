package alex.example.movies.ui.screens.maincontent

import alex.example.movies.R
import alex.example.movies.data.model.Film
import alex.example.movies.databinding.FragmentTVShowsBinding
import alex.example.movies.domain.model.FilmType
import alex.example.movies.ui.adapters.TvShowsAdapter
import alex.example.movies.ui.adapters.comparator.FilmComparator
import alex.example.movies.ui.adapters.pagingloadstate.PagingLoadingStateAdapter
import alex.example.movies.ui.screens.filter.FilterFragment
import alex.example.movies.ui.viewmodels.maincontent.TVShowsFragmentViewModel
import alex.example.movies.utils.BaseFragment
import alex.example.movies.utils.Const
import alex.example.movies.utils.Const.DETAIL_ARGUMENTS_FILM_TYPE_TAG
import alex.example.movies.utils.FilmItemClickListener
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TVShowsFragment : BaseFragment<FragmentTVShowsBinding, TVShowsFragmentViewModel>(FragmentTVShowsBinding::inflate, TVShowsFragmentViewModel::class.java) {

    private lateinit var tvShowsAdapter: TvShowsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
// Setup RV Adapter
            val layoutManager = GridLayoutManager(context, 2)
            tvShowsAdapter = TvShowsAdapter(FilmComparator).apply {

                viewLifecycleOwner.lifecycleScope.launch {
                    loadStateFlow.collectLatest {
                        loadErrorView.root.isVisible = it.refresh is LoadState.Error
                        swipeRefreshLayout.isVisible = it.refresh !is LoadState.Error
                        shimmer(it.refresh is LoadState.Loading)
                    }
                }

                // TODO: Improve implementation
                this.setOnClickListener(object: FilmItemClickListener {
                    override fun filmItemClick(id: Int, filmType: FilmType) {
                        val bundle = bundleOf(Const.DETAIL_ARGUMENTS_ID_TAG to id, DETAIL_ARGUMENTS_FILM_TYPE_TAG to filmType.name)
                        requireParentFragment().requireParentFragment().findNavController().navigate(R.id.action_mainContentFragment_to_filmDetailsFragment, bundle)
                    }
                })
            }
            val footerLoadingAdapter = PagingLoadingStateAdapter(tvShowsAdapter::retry)
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int =
                    if (position == tvShowsAdapter.itemCount && footerLoadingAdapter.itemCount > 0) 2 else 1
            }
            tvShowsRv.layoutManager = layoutManager
            tvShowsRv.adapter = tvShowsAdapter.withLoadStateFooter(
                footerLoadingAdapter
            )

            // Swipe refresh
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.callTvShowsApi()
            }

            // Toolbar
            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_filter -> {
                        showDialog()
                        true
                    }
                    else -> false
                }
            }

            // Retry Button
            loadErrorView.retryBtn.setOnClickListener { viewModel.callTvShowsApi() }

            // Fetch initial data
            viewModel.callTvShowsApi()

            // Collect movies
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.moviesState.collectLatest {
                    it?.let {
                        swipeRefreshLayout.isRefreshing = false
                        tvShowsAdapter.submitData(it)
                    }
                }
            }
        }
    }

    private fun showDialog() {
        val fragmentManager = childFragmentManager
        val newFragment = FilterFragment<Film>{
            viewModel.setFilter(it)
        }
        newFragment.show(fragmentManager, Const.FILTER_DIALOG_TAG)
    }

    private fun shimmer(showShimmer: Boolean) = with(binding) {
        shimmerLayout.isVisible = showShimmer
        tvShowsRv.isVisible = !showShimmer
        shimmerLayout.apply {
            if (showShimmer) startShimmer() else stopShimmer()
        }
    }

}