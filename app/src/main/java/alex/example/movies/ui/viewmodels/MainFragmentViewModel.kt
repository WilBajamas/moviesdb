package alex.example.movies.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainFragmentViewModel : ViewModel() {

    private val _logInState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loginState = _logInState.asStateFlow()

    fun init() {
        viewModelScope.launch {
            _logInState.emit(false)
        }
    }

}