package alex.example.movies.di.people

import alex.example.movies.data.remote.api.PeopleApi
import alex.example.movies.data.remote.datasource.PeopleDetailDataSource
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PeopleDetailDataSourceModule {

    @Provides
    @Singleton
    fun providePeopleDetailDataSource(
        peopleApi: PeopleApi,
        networkManager: NetworkRequestManager,
        dispatcherProvider: DispatcherProvider
    ): PeopleDetailDataSource {
        return PeopleDetailDataSource(peopleApi, networkManager, dispatcherProvider)
    }

}