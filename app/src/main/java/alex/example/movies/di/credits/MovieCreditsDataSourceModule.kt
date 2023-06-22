package alex.example.movies.di.credits

import alex.example.movies.data.remote.api.CreditsApi
import alex.example.movies.data.remote.datasource.MovieCreditsDataSource
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieCreditsDataSourceModule {

    @Provides
    @Singleton
    fun provideMovieCreditsDataSourceModule(
        creditsApi: CreditsApi,
        networkManager: NetworkRequestManager,
        dispatcherProvider: DispatcherProvider
    ): MovieCreditsDataSource =
        MovieCreditsDataSource(creditsApi, networkManager, dispatcherProvider)

}