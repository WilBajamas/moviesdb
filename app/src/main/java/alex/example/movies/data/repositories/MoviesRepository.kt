package alex.example.movies.data.repositories

import alex.example.movies.data.model.MoviePageResult
import alex.example.movies.data.remote.api.MoviesListApi
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesListApi: MoviesListApi,
    private val requestManager: NetworkRequestManager
) {

    suspend fun fetchMostPopularDescending(): Flow<Resource<MoviePageResult>> = flow {
        val result = requestManager.callApi {
            moviesListApi.fetchPopularMovies()
        }
        emit(result)
    }

    suspend fun fetchMostPopularAscending() {

    }

    suspend fun fetchTopRatedDescending() {

    }

    suspend fun fetchTopRatedAscending(){

    }

    suspend fun fetchReleaseDateDescending(){

    }

    suspend fun fetchReleaseDateAscending(){

    }

    suspend fun fetchAlphabetical(){

    }

    suspend fun fetchUpcoming(){

    }
}