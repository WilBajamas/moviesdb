package alex.example.movies.domain.use_case.auth

import alex.example.movies.data.repositories.AuthRepository
import android.util.Log
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(email: String, password: String, result: () -> Unit) =
        withContext(Dispatchers.IO) {
            // Request a requestToken
            val requestToken = authRepository.getRequestToken().body()?.request_token

            if (!requestToken.isNullOrEmpty()) {
                authRepository.saveRequestToken(requestToken)
                val loginResponse = authRepository.performLoginWithUsernamePassword(
                    email,
                    password,
                    requestToken
                )
                Log.i("Login Response: ", loginResponse.body()?.success.toString())
            } else {
                // TODO: Show error
            }

        }
}
