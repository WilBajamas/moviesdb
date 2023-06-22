package alex.example.movies.data.remote.datasource

import alex.example.movies.data.remote.api.FilmDetailApi
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvShowDetailDataSource @Inject constructor(
    private val filmDetailApi: FilmDetailApi,
    private val requestManager: NetworkRequestManager,
    private val dispatcherProvider: DispatcherProvider
) {

    fun fetchTvShowDetail(
        id: Int
    ) = flow {
        val result = withContext(dispatcherProvider.io) {
            requestManager.callApi {
                filmDetailApi.fetchTvShowDetail(id)
            }
        }

        emit(result)
    }
}
