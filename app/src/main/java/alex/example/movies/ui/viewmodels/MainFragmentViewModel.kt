package alex.example.movies.ui.viewmodels

import alex.example.movies.BuildConfig
import alex.example.movies.data.model.Session
import alex.example.movies.data.state.AuthState
import alex.example.movies.domain.use_case.auth.AuthUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _authState: MutableStateFlow<AuthState<Session>> =
        MutableStateFlow(AuthState.Loading)
    val authState = _authState.asStateFlow()

    fun init() {
        val apiKey = BuildConfig.API_KEY
        viewModelScope.launch {

            if (apiKey.isNotBlank()) {
                authUseCase {
                    if (it) _authState.emit(AuthState.SessionAvailable) else _authState.emit(
                        AuthState.SessionNull
                    )
                }
            } else {
                _authState.emit(AuthState.ApiKeyBlank)
            }
        }
    }
}