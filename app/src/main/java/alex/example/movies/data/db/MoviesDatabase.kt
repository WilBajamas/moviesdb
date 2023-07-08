package alex.example.movies.data.db

import alex.example.movies.data.local.dao.FilmDao
import alex.example.movies.data.local.entities.FilmEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FilmEntity::class], version = 1)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun filmDao(): FilmDao
}