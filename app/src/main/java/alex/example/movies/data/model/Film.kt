package alex.example.movies.data.model

data class Film(
    val poster_path: String?,
    val adult: Boolean,
    val overview: String,
    val release_date: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val original_title: String?,
    val original_language: String,
    val title: String?,
    val backdrop_path: String?,
    val popularity: Double,
    val vote_count: Int,
    val video: Boolean,
    val vote_average: Float,
    val first_air_date: String?,
    val name: String?,
)

open class FilmPageResult(
    val page: Int,
    val results: List<Film>,
    val total_results: Int,
    val total_pages: Int
)