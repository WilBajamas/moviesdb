package alex.example.movies.ui.viewmodels.login

import android.util.Patterns
import androidx.lifecycle.ViewModel

class LoginFragmentViewModel : ViewModel() {

    var email: String? = null
    var password: String? = null

    fun login() {

    }

    fun checkEmailInputValid(): Boolean {
        return !email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches()
    }

    fun checkPasswordInputValid(): Boolean {
        return !password.isNullOrEmpty() && password!!.length > 7
    }

}