package alex.example.movies.di.home

import alex.example.movies.data.remote.api.TvShowsListApi
import alex.example.movies.data.remote.datasource.TrendingTvShowsDataSource
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TrendingTvShowsDataSourceModule {

    @Provides
    @Singleton
    fun provideTrendingTvShowsDataSource(
        tvShowsListApi: TvShowsListApi,
        requestManager: NetworkRequestManager,
        dispatcherProvider: DispatcherProvider,
    ): TrendingTvShowsDataSource =
        TrendingTvShowsDataSource(tvShowsListApi, requestManager, dispatcherProvider)

}