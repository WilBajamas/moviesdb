package alex.example.movies.data.local.entities

import alex.example.movies.data.model.Genre
import alex.example.movies.data.model.Network
import alex.example.movies.data.model.People
import androidx.room.Entity

@Entity(tableName = "films")
data class FilmDetailEntity(
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
    val created_by: List<People>,
    val genres: List<Genre>,
    val filmType: String,
)
