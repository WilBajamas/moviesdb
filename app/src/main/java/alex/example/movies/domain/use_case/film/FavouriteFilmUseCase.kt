package alex.example.movies.domain.use_case.film

import alex.example.movies.data.model.FilmDetail
import alex.example.movies.data.repositories.FilmDetailRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class FavouriteFilmUseCase @Inject constructor(
    private val filmDetailRepository: FilmDetailRepository
) {

    suspend operator fun invoke(
        film: FilmDetail, filmType: String
    ) = filmDetailRepository.favouriteFilm(film, filmType)

}