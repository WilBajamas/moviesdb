package alex.example.movies.data.repositories

import alex.example.movies.data.model.FilmDetail
import alex.example.movies.data.remote.datasource.MovieDetailDataSource
import alex.example.movies.domain.model.FilmType
import alex.example.movies.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilmDetailRepository @Inject constructor(
    private val movieDetailDataSource: MovieDetailDataSource
) {

    suspend fun fetchFilmDetail(id: Int, filmType: String): Flow<Resource<FilmDetail>> {
        return when (filmType) {
            FilmType.MOVIE.name -> fetchMovieDetail(id)
            else -> TODO()
        }
    }

    private suspend fun fetchMovieDetail(id: Int) = movieDetailDataSource.fetchMovieDetails(id)


    private fun fetchTvShowDetail(id: Int) {

    }

}