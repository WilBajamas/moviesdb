package alex.example.movies.di.film

import alex.example.movies.data.db.MoviesDatabase
import alex.example.movies.data.local.dao.FilmDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalFilmDaoModule {

    @Provides
    @Singleton
    fun provideLocalFilmDao(moviesDatabase: MoviesDatabase): FilmDao = moviesDatabase.filmDao()

}