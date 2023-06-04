package alex.example.movies.data.remote.api

import alex.example.movies.data.model.PeopleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PeopleApi {

    @GET("trending/person/{time_window}")
    suspend fun fetchTrendingPeopleWithTimeWindow(
        @Path("time_window") id: String
    ): Response<PeopleResponse>

}