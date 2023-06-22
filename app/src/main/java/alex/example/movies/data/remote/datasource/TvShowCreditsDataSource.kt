package alex.example.movies.data.remote.datasource

import alex.example.movies.data.remote.api.CreditsApi
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvShowCreditsDataSource @Inject constructor(
    private val creditsApi: CreditsApi,
    private val requestManager: NetworkRequestManager,
    private val dispatcherProvider: DispatcherProvider
) {

    fun fetchTvShowCredits(
        id: Int
    ) = flow {
        val result = withContext(dispatcherProvider.io) {
            requestManager.callApi {
                creditsApi.fetchTvShowCredits(id)
            }
        }

        emit(result)
    }

}