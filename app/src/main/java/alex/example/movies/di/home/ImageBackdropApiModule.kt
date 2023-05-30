package alex.example.movies.di.home

import alex.example.movies.data.remote.api.ImageBackdropApi
import alex.example.movies.services.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ImageBackdropApiModule{

    @Provides
    @Singleton
    fun provideImageBackdropApi(
        retrofitInstance: RetrofitInstance
    ): ImageBackdropApi {
        return retrofitInstance.retrofit().create(ImageBackdropApi::class.java)
    }

}