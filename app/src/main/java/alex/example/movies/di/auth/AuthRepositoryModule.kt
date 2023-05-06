package alex.example.movies.di.auth

import alex.example.movies.data.remote.api.AuthApi
import alex.example.movies.data.repositories.AuthRepository
import alex.example.movies.services.NetworkRequestManager
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthRepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        sharedPreferences: SharedPreferences,
        authApi: AuthApi,
        requestManager: NetworkRequestManager
    ) : AuthRepository {
        return AuthRepository(sharedPreferences, authApi, requestManager)
    }
}