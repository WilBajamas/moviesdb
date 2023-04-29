package alex.example.movies.data.repositories

import alex.example.movies.data.AuthApi
import alex.example.movies.data.model.LoginRequest
import alex.example.movies.data.model.Session
import alex.example.movies.data.remote.RetrofitInstance
import alex.example.movies.utils.Const.LOGGED_IN
import alex.example.movies.utils.Const.REQUEST_TOKEN
import android.content.SharedPreferences
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val authApi: AuthApi,
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

    // TODO: Create wrapper request class -> detect errors
    suspend fun getRequestToken(): Response<Session>{
        return authApi.createRequestToken()
    }

    suspend fun performLoginWithUsernamePassword(email: String, password: String, requestToken: String): Response<Session> {
        val request = LoginRequest(email, password, requestToken)
        return authApi.createSessionWithLogin(request)
    }
}
