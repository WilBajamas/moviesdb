package alex.example.movies.domain.use_case.film

import alex.example.movies.data.model.FilmDetail
import alex.example.movies.data.repositories.FilmDetailRepository
import alex.example.movies.utils.Resource
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class FilmDetailsUseCase @Inject constructor(
    private val filmDetailRepository: FilmDetailRepository
) {

    suspend operator fun invoke(
        id: Int, filmType: String
    ): Flow<Resource<FilmDetail>> = filmDetailRepository.fetchFilmDetail(id, filmType)

}