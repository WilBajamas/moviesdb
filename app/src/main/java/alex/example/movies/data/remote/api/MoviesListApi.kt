package alex.example.movies.data.remote.api

import alex.example.movies.data.model.MoviePageResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesListApi {

    @GET("movie/popular")
    suspend fun fetchPopularMovies(
        @Query("page") page: Int = 1
    ): Response<MoviePageResult>

    @GET("discover/movie")
    suspend fun fetchMoviesWithQuery(
        @Query("page") page: Int?,
        @Query("sort_by") sortBy: String?,
        @Query("include_adult") includeAdult: Boolean = true,
        @Query("with_genres") withGenres: String?,
        @Query("with_original_language") withOriginalLanguage: String?,
        @Query("vote_count.gte") userScoreMinimum: Double?,
        @Query("vote_count.lte") userScoreMaximum: Double?,
    ): Response<MoviePageResult>

}