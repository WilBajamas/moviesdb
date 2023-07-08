package alex.example.movies.data.local.dao

import alex.example.movies.data.local.entities.FilmDetailEntity
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(vararg filmDetailEntity: FilmDetailEntity): Int

    @Delete
    suspend fun removeFilm(filmDetailEntity: FilmDetailEntity)

    @Query("SELECT * FROM my_table WHERE id = :filmId LIMIT 1")
    suspend fun getFilmDetailById(filmId: Int): Flow<FilmDetailEntity>

    @Query ("SELECT * FROM films WHERE filmType = :filmType")
    suspend fun getAllFilmsByType(filmType: Int): Flow<List<FilmDetailEntity>>

    @Query("DELETE FROM films WHERE id = :filmId")
    suspend fun deleteFilmById(filmDetailId: Int)
}