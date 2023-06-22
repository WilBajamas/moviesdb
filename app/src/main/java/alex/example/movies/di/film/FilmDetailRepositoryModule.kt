package alex.example.movies.di.film

import alex.example.movies.data.remote.datasource.MovieDetailDataSource
import alex.example.movies.data.repositories.FilmDetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FilmDetailRepositoryModule {

    @Provides
    @Singleton
    fun provideFilmDetailRepository(
        movieDetailDataSource: MovieDetailDataSource
    ): FilmDetailRepository {
        return FilmDetailRepository(movieDetailDataSource)
    }

}