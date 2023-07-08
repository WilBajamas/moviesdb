package alex.example.movies.ui.viewmodels.maincontent

import alex.example.movies.domain.use_case.auth.LogoutUseCase
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoreFragmentViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    fun logout() = logoutUseCase()

}