package alex.example.movies.domain.use_case.auth

import alex.example.movies.data.model.Session
import alex.example.movies.data.repositories.AuthRepository
import alex.example.movies.utils.Resource
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(
        email: String,
        password: String,
        result: (Resource<Session>) -> Unit
    ) =
        // Request a requestToken
        when (val session = authRepository.getRequestToken().first()) {
            is Resource.Success -> session.data?.let {
                authRepository.saveRequestToken(it.request_token)
                val loginSession = authRepository.performLoginWithUsernamePassword(
                    email,
                    password,
                    it.request_token
                ).first()
                if (loginSession is Resource.Success) authRepository.setLoggedIn()
                result(loginSession)
            }
            else -> {
                result(session)
            }
        }
}
