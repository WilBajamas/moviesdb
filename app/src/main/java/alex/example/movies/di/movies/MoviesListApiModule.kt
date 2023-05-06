package alex.example.movies.di.movies

import alex.example.movies.data.remote.api.MoviesListApi
import alex.example.movies.services.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MoviesListApiModule {

    @Provides
    @Singleton
    fun provideMoviesApi(
        retrofitInstance: RetrofitInstance
    ) : MoviesListApi{
        return retrofitInstance.retrofit().create(MoviesListApi::class.java)
    }
}