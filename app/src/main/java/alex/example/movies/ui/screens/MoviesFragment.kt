package alex.example.movies.ui.screens

import alex.example.movies.ui.viewmodels.MoviesFragmentViewModel
import alex.example.movies.R
import alex.example.movies.databinding.FragmentMoviesBinding
import alex.example.movies.ui.adapters.FilterAdapter
import alex.example.movies.ui.adapters.MoviesAdapter
import alex.example.movies.utils.BaseFragment
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        with(binding) {
            moviesRv.layoutManager = GridLayoutManager(context, 2)
            filtersRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            moviesAdapter = MoviesAdapter(viewModel.testMoviesData)
            filterAdapter = FilterAdapter(viewModel.filterData)

            moviesRv.adapter = moviesAdapter
            filtersRv.adapter = filterAdapter
        }
    }

}