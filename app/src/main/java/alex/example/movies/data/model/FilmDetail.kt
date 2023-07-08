package alex.example.movies.data.model

data class FilmDetail(
    val id: Int,
    val backdrop_path: String,
    val budget: Long?,
    val original_language: String,
    val title: String?,
    val name: String?,
    val overview: String,
    val poster_path: String,
    val release_date: String?,
    val first_air_date: String?,
    val revenue: Long?,
    val status: String,
    val tagline: String,
    val type: String?,
    val vote_average: Float,
    val networks: List<Network>?,
    val created_by: List<People>?,
    val genres: List<Genre>,
)

data class Genre(
    val id: Int,
    val name: String
)
