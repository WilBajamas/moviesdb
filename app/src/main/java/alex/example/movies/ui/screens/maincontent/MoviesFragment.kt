package alex.example.movies.ui.screens.maincontent

import alex.example.movies.R
import alex.example.movies.ui.viewmodels.maincontent.MoviesFragmentViewModel
import alex.example.movies.databinding.FragmentMoviesBinding
import alex.example.movies.ui.adapters.MoviesAdapter
import alex.example.movies.ui.screens.filter.FilterFragment
import alex.example.movies.utils.BaseFragment
import alex.example.movies.utils.Resource
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding, MoviesFragmentViewModel>(
    FragmentMoviesBinding::inflate, MoviesFragmentViewModel::class.java
) {

    private lateinit var moviesAdapter: MoviesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            moviesRv.layoutManager = GridLayoutManager(context, 2)
            moviesAdapter = MoviesAdapter()
            moviesRv.adapter = moviesAdapter

            swipeRefreshLayout.setOnRefreshListener {
                Log.i("REFRESH MOVIES:", "Refreshed")
                // TODO: Call this only after API call failed/succeeded
                swipeRefreshLayout.isRefreshing = false
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

        // TODO: Improve this
        parentFragmentManager.setFragmentResultListener(
            "requestKey", viewLifecycleOwner
        ) { _, result ->
            val languageValue = result.getString("languageId")
            Log.i("language: ", languageValue.toString())
        }
    }

    private fun showDialog() {
        val fragmentManager = parentFragmentManager
        val newFragment = FilterFragment()
        newFragment.show(fragmentManager, "dialog")
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
