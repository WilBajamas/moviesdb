package alex.example.movies.data.remote.api
import alex.example.movies.data.model.FilmDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmDetailApi {

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetail(@Path("movie_id") id: Int): Response<FilmDetail>

    @GET("tv/{series_id}")
    suspend fun fetchTvShowDetail(@Path("series_id") id: Int): Response<FilmDetail>

}
