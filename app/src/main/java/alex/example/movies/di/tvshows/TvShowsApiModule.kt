package alex.example.movies.di.tvshows

import alex.example.movies.data.remote.api.TvShowsListApi
import alex.example.movies.services.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TvShowsApiModule {

    @Provides
    @Singleton
    fun provideTvShowsApi(
        retrofitInstance: RetrofitInstance
    ): TvShowsListApi {
        return retrofitInstance.retrofit().create(TvShowsListApi::class.java)
    }

}