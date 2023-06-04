package alex.example.movies.di.home

import alex.example.movies.data.remote.api.PeopleApi
import alex.example.movies.data.remote.datasource.TrendingPeopleDataSource
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TrendingPeopleDataSourceModule {

    @Provides
    @Singleton
    fun provideTrendingPeopleDataSource(
        peopleApi: PeopleApi,
        requestManager: NetworkRequestManager,
        dispatcherProvider: DispatcherProvider,
    ): TrendingPeopleDataSource =
        TrendingPeopleDataSource(peopleApi, requestManager, dispatcherProvider)

}