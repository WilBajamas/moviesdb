package alex.example.movies.data.repositories

import alex.example.movies.data.AuthApi
import alex.example.movies.data.model.LoginRequest
import alex.example.movies.data.model.Session
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.Const.LOGGED_IN
import alex.example.movies.utils.Const.REQUEST_TOKEN
import alex.example.movies.utils.Resource
import android.content.SharedPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val authApi: AuthApi,
    private val requestManager: NetworkRequestManager
) {
    fun isLoggedIn(): Boolean {
        return !sharedPreferences.getString(LOGGED_IN, null).isNullOrEmpty()
    }

    fun isRequestTokenAvailable(): Boolean {
        return !sharedPreferences.getString(REQUEST_TOKEN, null).isNullOrEmpty()
    }

    fun saveRequestToken(requestToken: String) {
        sharedPreferences.edit().putString(REQUEST_TOKEN, requestToken).apply()
    }

    suspend fun getRequestToken(): Flow<Resource<Session>> = flow {
        val result = requestManager.callApi {
            authApi.createRequestToken()
        }
        emit(result)
    }

    suspend fun performLoginWithUsernamePassword(
        email: String,
        password: String,
        requestToken: String
    ): Flow<Resource<Session>>  = flow{
        val result = requestManager.callApi {
            authApi.createSessionWithLogin(LoginRequest(email, password, requestToken))
        }
        emit(result)
    }
}
