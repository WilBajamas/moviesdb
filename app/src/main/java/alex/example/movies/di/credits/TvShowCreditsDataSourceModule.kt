package alex.example.movies.di.credits

import alex.example.movies.data.remote.api.CreditsApi
import alex.example.movies.data.remote.datasource.TvShowCreditsDataSource
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TvShowCreditsDataSourceModule {

    @Provides
    @Singleton
    fun provideTvShowCreditsDataSource(
        creditsApi: CreditsApi,
        requestManager: NetworkRequestManager,
        dispatcherProvider: DispatcherProvider
    ): TvShowCreditsDataSource =
        TvShowCreditsDataSource(creditsApi, requestManager, dispatcherProvider)

}
