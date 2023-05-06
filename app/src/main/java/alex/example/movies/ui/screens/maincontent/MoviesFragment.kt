package alex.example.movies.ui.screens.maincontent

import alex.example.movies.ui.viewmodels.maincontent.MoviesFragmentViewModel
import alex.example.movies.R
import alex.example.movies.databinding.FragmentMoviesBinding
import alex.example.movies.data.model.Movie
import alex.example.movies.domain.ShowFilter
import alex.example.movies.ui.adapters.FilterAdapter
import alex.example.movies.ui.adapters.MoviesAdapter
import alex.example.movies.utils.BaseFragment
import alex.example.movies.utils.Resource
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding, MoviesFragmentViewModel>(
    FragmentMoviesBinding::inflate, MoviesFragmentViewModel::class.java
) {

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var filterAdapter: FilterAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Remove and use real-world API
        val filterData = listOf(
            ShowFilter(getString(R.string.popularity_descending)),
            ShowFilter(getString(R.string.popularity_ascending)),
            ShowFilter(getString(R.string.rating_descending)),
            ShowFilter(getString(R.string.rating_ascending)),
            ShowFilter(getString(R.string.release_date_descending)),
            ShowFilter(getString(R.string.release_date_ascending)),
            ShowFilter(getString(R.string.title_a_z)),
        )

        with(binding) {
            moviesRv.layoutManager = GridLayoutManager(context, 2)
            filtersRv.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            moviesAdapter = MoviesAdapter()
            filterAdapter = FilterAdapter(filterData)

            moviesRv.adapter = moviesAdapter
            filtersRv.adapter = filterAdapter

            swipeRefreshLayout.setOnRefreshListener {
                Log.i("REFRESH MOVIES:", "Refreshed")
                // TODO: Call this only after API call failed/succeeded
                swipeRefreshLayout.isRefreshing = false
            }

        }

        viewModel.init()

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
                        }
                    }
                }
            }
        }
    }

    private fun shimmer(showShimmer: Boolean) {
        if (showShimmer) {
            binding.shimmerLayout.startShimmer()
            binding.moviesRv.visibility = View.GONE
            binding.shimmerLayout.visibility = View.VISIBLE
        } else {
            binding.shimmerLayout.stopShimmer()
            binding.shimmerLayout.visibility = View.GONE
            binding.moviesRv.visibility = View.VISIBLE
        }
    }

}