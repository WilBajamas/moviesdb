package alex.example.movies.data.remote.api

import alex.example.movies.data.model.FilmPageResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TvShowsListApi {

    @GET("trending/tv/{time_window}")
    suspend fun fetchTrendingMoviesWithTimeWindow(
        @Path("time_window") id: String
    ): Response<FilmPageResult>
}