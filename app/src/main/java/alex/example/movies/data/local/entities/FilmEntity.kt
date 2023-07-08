package alex.example.movies.data.local.entities

import androidx.room.PrimaryKey

data class FilmEntity(
    @PrimaryKey val id: Int,
    val poster_path: String?,
    val release_date: String?,
    val genre_ids: List<Int>,
    val title: String?,
    val vote_average: Float,
    val first_air_date: String?,
    val name: String?,
    val filmType: String
)
