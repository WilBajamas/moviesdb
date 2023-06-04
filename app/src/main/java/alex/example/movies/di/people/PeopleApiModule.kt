package alex.example.movies.di.people

import alex.example.movies.data.remote.api.PeopleApi
import alex.example.movies.services.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PeopleApiModule {

    @Provides
    @Singleton
    fun providePeopleApi(
        retrofitInstance: RetrofitInstance
    ): PeopleApi {
        return retrofitInstance.retrofit().create(PeopleApi::class.java)
    }

}