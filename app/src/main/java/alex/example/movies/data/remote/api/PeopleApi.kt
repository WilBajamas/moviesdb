package alex.example.movies.data.remote.api

import alex.example.movies.data.model.*
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

    @GET("person/{person_id}")
    suspend fun fetchPersonDetails(
        @Path("person_id") personId: Int?,
    ): Response<People>

    @GET("person/{person_id}/movie_credits")
    suspend fun fetchPersonMovieCredits(
        @Path("person_id") personId: Int?,
    ): Response<FilmCredit>

}