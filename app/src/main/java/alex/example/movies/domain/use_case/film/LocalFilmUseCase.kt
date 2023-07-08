package alex.example.movies.domain.use_case.film

import alex.example.movies.data.mapper.mapToModel
import alex.example.movies.data.model.FilmDetail
import alex.example.movies.data.repositories.FilmDetailRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class LocalFilmUseCase @Inject constructor(
    private val filmDetailRepository: FilmDetailRepository
) {

    suspend operator fun invoke(
        filmId: Int
    ): FilmDetail? = filmDetailRepository.getLocalFilm(filmId)?.mapToModel()

}