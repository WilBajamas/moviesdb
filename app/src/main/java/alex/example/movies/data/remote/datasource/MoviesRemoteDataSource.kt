package alex.example.movies.data.remote.datasource

import alex.example.movies.data.model.MoviePageResult
import alex.example.movies.data.remote.api.MoviesListApi
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import alex.example.movies.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val moviesListApi: MoviesListApi,
    private val requestManager: NetworkRequestManager,
    private val dispatcher: DispatcherProvider
) {

    // Descending by default
    suspend fun fetchMostPopular(page: Int = 1): Flow<Resource<MoviePageResult>> = flow {
        val result: Resource<MoviePageResult>
        withContext(dispatcher.io) {
            result = requestManager.callApi {
                moviesListApi.fetchPopularMovies(page)
            }
        }
        emit(result)
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