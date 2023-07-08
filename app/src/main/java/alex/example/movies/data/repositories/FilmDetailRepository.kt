package alex.example.movies.data.repositories

import alex.example.movies.data.local.dao.FilmDao
import alex.example.movies.data.mapper.mapToEntity
import alex.example.movies.data.model.FilmDetail
import alex.example.movies.data.remote.datasource.MovieDetailDataSource
import alex.example.movies.data.remote.datasource.TvShowDetailDataSource
import alex.example.movies.domain.model.FilmType
import alex.example.movies.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FilmDetailRepository @Inject constructor(
    private val movieDetailDataSource: MovieDetailDataSource,
    private val tvShowDetailDataSource: TvShowDetailDataSource,
    private val filmDao: FilmDao,
) {

    suspend fun fetchFilmDetail(id: Int, filmType: String): Flow<Resource<FilmDetail>> {
        return when (filmType) {
            FilmType.MOVIE.name -> movieDetailDataSource.fetchMovieDetails(id)
            else -> tvShowDetailDataSource.fetchTvShowDetail(id)
        }
    }

    suspend fun favouriteFilm(film: FilmDetail, filmType: String) = flow {
        emit(filmDao.insertFilm(film.mapToEntity(filmType)) > 0)
    }

    suspend fun unfavouriteFilm(film: FilmDetail, filmType: String) =
        flow {
            try {
                filmDao.removeFilm(film.mapToEntity(filmType))
                emit(false)

            } catch (e: java.lang.Exception) {
                emit(true)
            }
        }

    suspend fun getLocalFilm(filmId: Int) = filmDao.getFilmDetailById(filmId)

}
