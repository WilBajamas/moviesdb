package alex.example.movies.data.repositories

import alex.example.movies.data.model.Credit
import alex.example.movies.data.remote.datasource.MovieCreditsDataSource
import alex.example.movies.domain.model.FilmType
import alex.example.movies.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreditsRepository @Inject constructor(
    private val movieCreditsDataSource: MovieCreditsDataSource
) {

    fun fetchCredits(id: Int, filmType: String): Flow<Resource<Credit>> {
        return when (filmType) {
            FilmType.MOVIE.name -> movieCreditsDataSource.fetchMovieCredits(id)
            else -> TODO()
        }
    }
}
