package alex.example.movies.data.remote.api

import alex.example.movies.data.model.Movie
import alex.example.movies.data.model.MoviePageResult
import retrofit2.Response
import retrofit2.http.GET

interface MoviesListApi {

    @GET("movie/popular")
    suspend fun fetchPopularMovies(): Response<MoviePageResult>

}