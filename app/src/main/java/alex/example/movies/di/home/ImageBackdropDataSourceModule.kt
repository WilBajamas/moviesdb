package alex.example.movies.di.home

import alex.example.movies.data.remote.api.ImageBackdropApi
import alex.example.movies.data.remote.datasource.ImageBackdropDataSource
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ImageBackdropDataSourceModule {

    @Provides
    @Singleton
    fun provideImageBackdropDataSource(
        imageBackdropApi: ImageBackdropApi,
        requestManager: NetworkRequestManager,
        dispatcherProvider: DispatcherProvider
    ): ImageBackdropDataSource {
        return ImageBackdropDataSource(imageBackdropApi, requestManager, dispatcherProvider)
    }

}