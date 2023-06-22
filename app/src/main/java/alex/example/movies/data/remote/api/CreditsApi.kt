package alex.example.movies.data.remote.api

import alex.example.movies.data.model.Credit
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CreditsApi {

    @GET("movie/{movie_id}/credits")
    suspend fun fetchMovieCredits(@Path("movie_id") id: Int): Response<Credit>

    @GET("tv/{series_id}/credits")
    suspend fun fetchTvShowCredits(@Path("series_id") id: Int): Response<Credit>

}