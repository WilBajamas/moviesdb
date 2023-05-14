package alex.example.movies.data.repositories

import alex.example.movies.data.model.Session
import alex.example.movies.data.remote.datasource.AuthDataSource
import alex.example.movies.utils.Const.LOGGED_IN
import alex.example.movies.utils.Const.REQUEST_TOKEN
import alex.example.movies.utils.Resource
import android.content.SharedPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val authDataSource: AuthDataSource
) {
    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(LOGGED_IN, false)
    }

    fun setLoggedIn() {
        sharedPreferences.edit().putBoolean(LOGGED_IN, true).apply()
    }

    fun isRequestTokenAvailable(): Boolean {
        return !sharedPreferences.getString(REQUEST_TOKEN, null).isNullOrEmpty()
    }

    fun saveRequestToken(requestToken: String) {
        sharedPreferences.edit().putString(REQUEST_TOKEN, requestToken).apply()
    }

    suspend fun getRequestToken(): Flow<Resource<Session>> = authDataSource.getRequestToken()

    suspend fun performLoginWithUsernamePassword(
        email: String,
        password: String,
        requestToken: String
    ): Flow<Resource<Session>> =
        authDataSource.performLoginWithUsernamePassword(email, password, requestToken)
}
