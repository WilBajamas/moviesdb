package alex.example.movies.di.movies

import alex.example.movies.data.remote.api.MoviesListApi
import alex.example.movies.data.remote.datasource.MoviesRemoteDataSource
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class MoviesDataSourceModule {

    @Provides
    @Singleton
    fun provideMoviesRemoteDataSource(
        moviesListApi: MoviesListApi,
        requestManager: NetworkRequestManager,
        dispatcher: DispatcherProvider
    ): MoviesRemoteDataSource {
        return MoviesRemoteDataSource(moviesListApi, requestManager, dispatcher)
    }
}