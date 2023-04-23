package alex.example.movies.ui.viewmodels

import alex.example.movies.BuildConfig
import alex.example.movies.data.model.Session
import alex.example.movies.data.repositories.AuthRepository
import alex.example.movies.data.state.AuthState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {


    private val _authState: MutableStateFlow<AuthState<Session>> =
        MutableStateFlow(AuthState.Loading)
    val authState = _authState.asStateFlow()

    fun init() {
        val apiKey = BuildConfig.API_KEY
        viewModelScope.launch {
            val isUserLoggedIn =
                authRepository.isLoggedIn()
            val isRequestTokenAvailable = authRepository.isRequestTokenAvailable()

            if (apiKey.isNotBlank()) {
                if (isUserLoggedIn && isRequestTokenAvailable) {
                    // Proceed to Home Screen
                    _authState.emit(AuthState.SessionAvailable)
                } else {
                    // Proceeed to Log In Screen
                    _authState.emit(AuthState.SessionNull)
                }
            } else {
                _authState.emit(AuthState.ApiKeyBlank)
            }
        }
    }
}