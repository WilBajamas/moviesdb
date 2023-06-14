package alex.example.movies.di.people

import alex.example.movies.data.remote.api.PeopleApi
import alex.example.movies.data.remote.datasource.TrendingPeopleDataSource
import alex.example.movies.data.repositories.PeopleRepository
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PeopleRepositoryModule {

    @Provides
    @Singleton
    fun providePeopleRepository(
        trendingPeopleDataSource: TrendingPeopleDataSource,
        requestManager: NetworkRequestManager,
        dispatcher: DispatcherProvider,
        peopleApi: PeopleApi
    ): PeopleRepository =
        PeopleRepository(trendingPeopleDataSource, requestManager, dispatcher, peopleApi)

}