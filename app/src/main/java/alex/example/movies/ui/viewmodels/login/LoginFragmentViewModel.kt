package alex.example.movies.ui.viewmodels.login

import alex.example.movies.data.model.Session
import alex.example.movies.domain.use_case.auth.LoginUseCase
import alex.example.movies.utils.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginResponse = MutableLiveData<Resource<Session>>()
    val loginResponse: LiveData<Resource<Session>>
        get() = _loginResponse

    var username: String? = null
    var password: String? = null

    fun login() {
        viewModelScope.launch {
            _loginResponse.value = Resource.Loading()
            loginUseCase(username!!, password!!) {
                _loginResponse.postValue(it)
            }
        }
    }

    fun checkEmailInputValid(): Boolean {
        return !username.isNullOrEmpty()
    }

    fun checkPasswordInputValid(): Boolean {
        return !password.isNullOrEmpty() && password!!.length > 7
    }

}