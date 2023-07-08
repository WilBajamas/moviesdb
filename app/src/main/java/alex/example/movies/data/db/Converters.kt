package alex.example.movies.data.db

import alex.example.movies.data.model.Genre
import alex.example.movies.data.model.Network
import alex.example.movies.data.model.People
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromNetwork(value: List<Network>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toNetworkList(value: String): List<Network>? {
        val type = object : TypeToken<List<Network>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromPeople(value: List<People>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toPeopleList(value: String): List<People>? {
        val type = object : TypeToken<List<People>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromGenre(value: List<Genre>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toGenreList(value: String): List<Genre>? {
        val type = object : TypeToken<List<Genre>>() {}.type
        return gson.fromJson(value, type)
    }
}