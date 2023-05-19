package alex.example.movies.data.repositories

import alex.example.movies.data.remote.datasource.MoviesRemoteDataSource
import alex.example.movies.ui.model.FilterRequest
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) {

    suspend fun fetchMovies(
        filterRequest: FilterRequest
    ) = moviesRemoteDataSource.fetchMovies(
        filterRequest = filterRequest
    )

}
