package alex.example.movies.data.repositories

import alex.example.movies.utils.Const.LOGGED_IN
import alex.example.movies.utils.Const.REQUEST_TOKEN
import android.content.SharedPreferences
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun isLoggedIn(): Boolean {
        return !sharedPreferences.getString(LOGGED_IN, null).isNullOrEmpty()
    }

    fun isRequestTokenAvailable(): Boolean {
        return !sharedPreferences.getString(REQUEST_TOKEN, null).isNullOrEmpty()
    }
}