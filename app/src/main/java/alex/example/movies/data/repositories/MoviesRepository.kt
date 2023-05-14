package alex.example.movies.data.repositories

import alex.example.movies.data.remote.datasource.MoviesRemoteDataSource
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) {

    suspend fun fetchMostPopularDescending() = moviesRemoteDataSource.fetchMostPopular()

    suspend fun fetchMostPopularAscending() {

    }

}