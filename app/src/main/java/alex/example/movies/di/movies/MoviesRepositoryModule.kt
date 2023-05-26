package alex.example.movies.di.movies

import alex.example.movies.data.remote.api.MoviesListApi
import alex.example.movies.data.repositories.MoviesRepository
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MoviesRepositoryModule {

    @Provides
    @Singleton
    fun provideMoviesRepository(
        moviesListApi: MoviesListApi,
        requestManager: NetworkRequestManager,
        dispatcher: DispatcherProvider,
    ): MoviesRepository {
        return MoviesRepository(moviesListApi, requestManager, dispatcher)
    }
}
