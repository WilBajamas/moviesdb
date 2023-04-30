package alex.example.movies.domain.use_case.auth

import alex.example.movies.data.repositories.AuthRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class AuthUseCase @Inject constructor(
    private val authRepository: AuthRepository,
){
    suspend operator fun invoke (onAuthenticated: suspend (Boolean) -> Unit) {
        onAuthenticated(authRepository.isLoggedIn() && authRepository.isRequestTokenAvailable())
    }
}
