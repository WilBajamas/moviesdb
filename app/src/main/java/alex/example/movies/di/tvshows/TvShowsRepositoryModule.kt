package alex.example.movies.di.tvshows

import alex.example.movies.data.remote.datasource.TrendingTvShowsDataSource
import alex.example.movies.data.repositories.TvShowsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TvShowsRepositoryModule {

    @Provides
    @Singleton
    fun provideTvShowsRepository(
        trendingTvShowsDataSource: TrendingTvShowsDataSource
    ): TvShowsRepository = TvShowsRepository(trendingTvShowsDataSource)
}