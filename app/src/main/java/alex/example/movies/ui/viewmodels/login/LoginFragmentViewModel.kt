package alex.example.movies.ui.viewmodels.login

import alex.example.movies.domain.use_case.auth.LoginUseCase
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var email: String? = null
    var password: String? = null

    fun login() {
        viewModelScope.launch {
            loginUseCase(email!!, password!!) {

            }
        }
    }

    fun checkEmailInputValid(): Boolean {
        return !email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches()
    }

    fun checkPasswordInputValid(): Boolean {
        return !password.isNullOrEmpty() && password!!.length > 7
    }

}