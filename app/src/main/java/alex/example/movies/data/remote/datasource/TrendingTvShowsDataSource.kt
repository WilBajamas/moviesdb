package alex.example.movies.data.remote.datasource

import alex.example.movies.data.model.FilmPageResult
import alex.example.movies.data.remote.api.TvShowsListApi
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import alex.example.movies.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrendingTvShowsDataSource @Inject constructor(
    private val tvShowsListApi: TvShowsListApi,
    private val requestManager: NetworkRequestManager,
    private val dispatcher: DispatcherProvider
) {

    suspend fun fetchTrendingTvShows(
        timeWindow: String
    ): Flow<Resource<FilmPageResult>> = flow {
        val result: Resource<FilmPageResult> = withContext(dispatcher.io) {
            requestManager.callApi {
                tvShowsListApi.fetchTrendingMoviesWithTimeWindow(
                    timeWindow
                )
            }
        }
        emit(result)
    }
}
