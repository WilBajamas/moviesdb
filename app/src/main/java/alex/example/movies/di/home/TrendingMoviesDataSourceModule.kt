package alex.example.movies.di.home

import alex.example.movies.data.remote.datasource.TrendingMoviesDataSource
import alex.example.movies.data.remote.api.MoviesListApi
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TrendingMoviesDataSourceModule {

    @Provides
    @Singleton
    fun provideTrendingMoviesDataSource(
        moviesListApi: MoviesListApi,
        requestManager: NetworkRequestManager,
        dispatcher: DispatcherProvider
    ): TrendingMoviesDataSource =
        TrendingMoviesDataSource(moviesListApi, requestManager, dispatcher)

}