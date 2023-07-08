package alex.example.movies.di.tvshows

import alex.example.movies.data.local.dao.FilmDao
import alex.example.movies.data.remote.api.TvShowsListApi
import alex.example.movies.data.remote.datasource.TrendingTvShowsDataSource
import alex.example.movies.data.repositories.TvShowsRepository
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
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
        tvShowsListApi: TvShowsListApi,
        requestManager: NetworkRequestManager,
        dispatcher: DispatcherProvider,
        trendingTvShowsDataSource: TrendingTvShowsDataSource,
        filmDao: FilmDao
    ): TvShowsRepository =
        TvShowsRepository(
            tvShowsListApi,
            requestManager,
            dispatcher,
            trendingTvShowsDataSource,
            filmDao
        )
}