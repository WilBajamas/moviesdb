package alex.example.movies.data.repositories

import alex.example.movies.data.model.BackdropResponse
import alex.example.movies.data.remote.datasource.ImageBackdropDataSource
import alex.example.movies.utils.Resource
import javax.inject.Inject

class ImageBackdropRepository @Inject constructor(
    private val imageBackdropDataSource: ImageBackdropDataSource
) {

    suspend fun fetchImageBackdropCollection(): Resource<BackdropResponse> =
        imageBackdropDataSource.fetchImageBackdrops()

}
