package alex.example.movies.data.repositories

import alex.example.movies.data.model.FilmDetail
import alex.example.movies.data.remote.datasource.MovieDetailDataSource
import alex.example.movies.data.remote.datasource.TvShowDetailDataSource
import alex.example.movies.domain.model.FilmType
import alex.example.movies.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilmDetailRepository @Inject constructor(
    private val movieDetailDataSource: MovieDetailDataSource,
    private val tvShowDetailDataSource: TvShowDetailDataSource
) {

    suspend fun fetchFilmDetail(id: Int, filmType: String): Flow<Resource<FilmDetail>> {
        return when (filmType) {
            FilmType.MOVIE.name -> movieDetailDataSource.fetchMovieDetails(id)
            else -> tvShowDetailDataSource.fetchTvShowDetail(id)
        }
    }
}
