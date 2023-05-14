package alex.example.movies.di.movies

import alex.example.movies.data.remote.datasource.MoviesRemoteDataSource
import alex.example.movies.data.repositories.MoviesRepository
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
        moviesRemoteDataSource: MoviesRemoteDataSource
    ): MoviesRepository {
        return MoviesRepository(moviesRemoteDataSource)
    }
}