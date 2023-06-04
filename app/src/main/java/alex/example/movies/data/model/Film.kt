package alex.example.movies.data.model

data class Film(
    val poster_path: String?,
    val release_date: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val title: String?,
    val vote_average: Float,
    val first_air_date: String?,
    val name: String?,
)

open class FilmPageResult(
    val page: Int,
    val results: List<Film>,
    val total_pages: Int
)