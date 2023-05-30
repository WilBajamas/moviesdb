package alex.example.movies.di.home

import alex.example.movies.data.remote.datasource.ImageBackdropDataSource
import alex.example.movies.data.repositories.ImageBackdropRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ImageBackdropRepositoryModule {

    @Provides
    fun provideImageBackdropRepository(
        imageBackdropDataSource: ImageBackdropDataSource
    ): ImageBackdropRepository {
        return ImageBackdropRepository(imageBackdropDataSource)
    }
}