package alex.example.movies.data.remote.datasource

import alex.example.movies.data.model.BackdropResponse
import alex.example.movies.data.remote.api.ImageBackdropApi
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.Const
import alex.example.movies.utils.DispatcherProvider
import alex.example.movies.utils.Resource
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ImageBackdropDataSource @Inject constructor(
    private val imageBackdropApi: ImageBackdropApi,
    private val requestManager: NetworkRequestManager,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend fun fetchImageBackdrops(): Resource<BackdropResponse> {
        var result: Resource<BackdropResponse>
        withContext(dispatcherProvider.io) {
            result = requestManager.callApi {
                imageBackdropApi.fetchBackdropImages(Const.DEFAULT_BACKDROP_COLLECTION_ID)
            }
        }

        return result
    }

}