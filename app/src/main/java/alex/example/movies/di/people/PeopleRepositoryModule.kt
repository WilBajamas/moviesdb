package alex.example.movies.di.people

import alex.example.movies.data.remote.datasource.TrendingPeopleDataSource
import alex.example.movies.data.repositories.PeopleRepository
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
        trendingPeopleDataSource: TrendingPeopleDataSource
    ): PeopleRepository = PeopleRepository(trendingPeopleDataSource)

}