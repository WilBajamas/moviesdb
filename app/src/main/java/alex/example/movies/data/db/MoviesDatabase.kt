package alex.example.movies.data.db

import alex.example.movies.data.local.dao.FilmDao
import alex.example.movies.data.local.entities.FilmDetailEntity
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [FilmDetailEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun filmDao(): FilmDao
}