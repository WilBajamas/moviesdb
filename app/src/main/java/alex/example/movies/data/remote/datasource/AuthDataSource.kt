package alex.example.movies.data.remote.datasource

import alex.example.movies.data.model.LoginRequest
import alex.example.movies.data.model.Session
import alex.example.movies.data.remote.api.AuthApi
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import alex.example.movies.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val authApi: AuthApi,
    private val requestManager: NetworkRequestManager,
    private val dispatcher: DispatcherProvider,
) {
    suspend fun getRequestToken(): Flow<Resource<Session>> = flow {
        var result: Resource<Session>
        withContext(dispatcher.io) {
            result = requestManager.callApi {
                authApi.createRequestToken()
            }
        }
        emit(result)
    }

    suspend fun performLoginWithUsernamePassword(
        email: String,
        password: String,
        requestToken: String
    ): Flow<Resource<Session>> = flow {
        var result: Resource<Session>
        withContext(dispatcher.io) {
            result = requestManager.callApi {
                authApi.createSessionWithLogin(LoginRequest(email, password, requestToken))
            }
        }
        emit(result)
    }
}