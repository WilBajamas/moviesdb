package alex.example.movies.di.film

import alex.example.movies.data.remote.api.FilmDetailApi
import alex.example.movies.services.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FilmDetailApiModule {

    @Provides
    @Singleton
    fun provideFilmDetailApi(
        retrofitInstance: RetrofitInstance
    ): FilmDetailApi {
        return retrofitInstance.retrofit().create(FilmDetailApi::class.java)
    }

}