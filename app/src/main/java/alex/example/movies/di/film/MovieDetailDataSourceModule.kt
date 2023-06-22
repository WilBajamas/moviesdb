package alex.example.movies.di.film

import alex.example.movies.data.remote.api.FilmDetailApi
import alex.example.movies.data.remote.datasource.MovieDetailDataSource
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieDetailDataSourceModule {

    @Provides
    @Singleton
    fun provideMovieDetailDataSource(
        filmDetailApi: FilmDetailApi,
        networkManager: NetworkRequestManager,
        dispatcher: DispatcherProvider,
    ): MovieDetailDataSource {
        return MovieDetailDataSource(filmDetailApi, networkManager, dispatcher)
    }

}