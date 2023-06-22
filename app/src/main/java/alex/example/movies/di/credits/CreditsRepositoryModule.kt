package alex.example.movies.di.credits

import alex.example.movies.data.remote.datasource.MovieCreditsDataSource
import alex.example.movies.data.remote.datasource.TvShowCreditsDataSource
import alex.example.movies.data.repositories.CreditsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CreditsRepositoryModule {

    @Provides
    @Singleton
    fun provideCreditsRepository(
        movieCreditsDataSource: MovieCreditsDataSource,
        tvShowCreditsDataSource: TvShowCreditsDataSource
    ): CreditsRepository = CreditsRepository(movieCreditsDataSource, tvShowCreditsDataSource)

}