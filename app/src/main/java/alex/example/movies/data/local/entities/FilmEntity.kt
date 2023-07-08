package alex.example.movies.data.local.entities


data class FilmEntity(
    val id: Int,
    val poster_path: String?,
    val release_date: String?,
    val genre_ids: List<Int>,
    val title: String?,
    val vote_average: Float,
    val first_air_date: String?,
    val name: String?,
    val filmType: String
)
