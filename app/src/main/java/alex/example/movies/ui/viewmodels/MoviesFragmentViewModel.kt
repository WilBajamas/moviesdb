package alex.example.movies.ui.viewmodels

import alex.example.movies.domain.Movies
import alex.example.movies.domain.ShowFilter
import androidx.lifecycle.ViewModel

class MoviesFragmentViewModel : ViewModel() {

    // TODO: Use real world data
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

    val filterData = listOf<ShowFilter>(
        ShowFilter("Popular"),
        ShowFilter("Release Date"),
        ShowFilter("A-Z"),
        ShowFilter("A-Z"),
        ShowFilter("A-Z"),
        ShowFilter("A-Z"),
        ShowFilter("A-Z"),
        ShowFilter("A-Z"),
        ShowFilter("A-Z"),
    )

}