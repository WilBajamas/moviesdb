package alex.example.movies.ui.screens

import alex.example.movies.ui.viewmodels.MoviesFragmentViewModel
import alex.example.movies.R
import alex.example.movies.databinding.FragmentMoviesBinding
import alex.example.movies.domain.Movies
import alex.example.movies.domain.ShowFilter
import alex.example.movies.ui.adapters.FilterAdapter
import alex.example.movies.ui.adapters.MoviesAdapter
import alex.example.movies.utils.BaseFragment
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

class MoviesFragment : BaseFragment<FragmentMoviesBinding, MoviesFragmentViewModel>(
    FragmentMoviesBinding::inflate,
    MoviesFragmentViewModel::class.java
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

        val testMoviesData = listOf<Movies>(
            Movies("SampleSampleSampleSampleSampleSampleSampleSampleSampleSampleSampleSampleSampleSampleSampleSampleSampleSampleSampleSampleSample", "Mar 01, 2023", "https://image.tmdb.org/t/p/original/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg", "100%"),
            Movies("Sample", "Mar 01, 2023", "https://image.tmdb.org/t/p/original/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg", "100%"),
            Movies("Sample", "Mar 01, 2023", "https://image.tmdb.org/t/p/original/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg", "100%"),
            Movies("Sample", "Mar 01, 2023", "https://image.tmdb.org/t/p/original/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg", "100%"),
            Movies("Sample", "Mar 01, 2023", "https://image.tmdb.org/t/p/original/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg", "100%"),
            Movies("Sample", "Mar 01, 2023", "https://image.tmdb.org/t/p/original/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg", "100%"),
            Movies("Sample", "Mar 01, 2023", "https://image.tmdb.org/t/p/original/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg", "100%"),
            Movies("Sample", "Mar 01, 2023", "https://image.tmdb.org/t/p/original/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg", "100%"),
            Movies("Sample", "Mar 01, 2023", "https://image.tmdb.org/t/p/original/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg", "100%"),
            Movies("Sample", "Mar 01, 2023", "https://image.tmdb.org/t/p/original/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg", "100%"),
            Movies("Sample", "Mar 01, 2023", "https://image.tmdb.org/t/p/original/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg", "100%"),
            Movies("Sample", "Mar 01, 2023", "https://image.tmdb.org/t/p/original/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg", "100%"),
        )

        with(binding) {
            moviesRv.layoutManager = GridLayoutManager(context, 2)
            filtersRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            moviesAdapter = MoviesAdapter(testMoviesData)
            filterAdapter = FilterAdapter(filterData)

            moviesRv.adapter = moviesAdapter
            filtersRv.adapter = filterAdapter

            swipeRefreshLayout.setOnRefreshListener {
                Log.i("REFRESH MOVIES:", "Refreshed")
                // TODO: Call this only after API call failed/succeeded
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

}