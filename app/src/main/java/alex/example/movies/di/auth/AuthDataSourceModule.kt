package alex.example.movies.di.auth

import alex.example.movies.data.remote.api.AuthApi
import alex.example.movies.data.remote.datasource.AuthDataSource
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AuthDataSourceModule {
    @Provides
    @Singleton
    fun provideAuthDataSource(
        authApi: AuthApi,
        requestManager: NetworkRequestManager,
        dispatcherProvider: DispatcherProvider
    ): AuthDataSource {
        return AuthDataSource(authApi, requestManager, dispatcherProvider)
    }

}