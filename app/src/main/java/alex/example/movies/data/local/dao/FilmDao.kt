package alex.example.movies.data.local.dao

import alex.example.movies.data.local.entities.FilmDetailEntity
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(filmDetailEntity: FilmDetailEntity): Long

    @Delete
    suspend fun removeFilm(filmDetailEntity: FilmDetailEntity)

    @Query("SELECT * FROM films WHERE id = :filmId LIMIT 1")
    suspend fun getFilmDetailById(filmId: Int): FilmDetailEntity?

    @Query("SELECT * FROM films WHERE filmType = :filmType")
    fun getAllFilmsByType(filmType: Int): Flow<List<FilmDetailEntity>>

}
