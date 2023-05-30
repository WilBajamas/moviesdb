package alex.example.movies.data.remote.api

import alex.example.movies.data.model.BackdropResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageBackdropApi{

    @GET("collection/{collection_id}/images")
    suspend fun fetchBackdropImages(@Path("collection_id") id: Int): Response<BackdropResponse>

}
