package alex.example.movies.data.remote.datasource

import alex.example.movies.data.model.FilmPageResult
import alex.example.movies.data.remote.api.MoviesListApi
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import alex.example.movies.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class TrendingMoviesDataSource @Inject constructor(
    private val moviesListApi: MoviesListApi,
    private val requestManager: NetworkRequestManager,
    private val dispatcher: DispatcherProvider
) {

    // Descending by default
    suspend fun fetchTrendingMovies(
        timeWindow: String
    ): Flow<Resource<FilmPageResult>> = flow {
        emit(Resource.Loading())
        val result: Resource<FilmPageResult> = withContext(dispatcher.io) {
            requestManager.callApi {
                moviesListApi.fetchTrendingMoviesWithTimeWindow(
                    timeWindow
                )
            }
        }
        emit(result)
    }
}