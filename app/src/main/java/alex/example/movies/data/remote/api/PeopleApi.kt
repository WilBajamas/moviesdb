package alex.example.movies.data.remote.api

import alex.example.movies.data.model.PeopleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleApi {

    @GET("trending/person/{time_window}")
    suspend fun fetchTrendingPeopleWithTimeWindow(
        @Path("time_window") id: String
    ): Response<PeopleResponse>

    @GET("person/popular")
    suspend fun fetchPopularPeople(
        @Query("page") page: Int?
    ): Response<PeopleResponse>

    @GET("search/person")
    suspend fun fetchSearchPeople(
        @Query("page") page: Int?,
        @Query("query") search: String?
    ): Response<PeopleResponse>

}