package alex.example.movies.data.remote.datasource

import alex.example.movies.data.remote.api.CreditsApi
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieCreditsDataSource @Inject constructor(
    private val creditsApi: CreditsApi,
    private val networkManager: NetworkRequestManager,
    private val dispatcherProvider: DispatcherProvider
) {

    fun fetchMovieCredits(
        id: Int
    ) = flow {
        val result = withContext(dispatcherProvider.io) {
            networkManager.callApi {
                creditsApi.fetchMovieCredits(id)
            }
        }

        emit(result)
    }

}