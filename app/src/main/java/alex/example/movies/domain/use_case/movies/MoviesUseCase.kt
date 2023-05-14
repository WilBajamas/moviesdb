package alex.example.movies.domain.use_case.movies

import alex.example.movies.data.model.MoviePageResult
import alex.example.movies.data.repositories.MoviesRepository
import alex.example.movies.domain.model.ListType
import alex.example.movies.utils.Resource
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class MoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
) {

    // TODO: Save to Room here, return only required data model for UI
    suspend operator fun invoke(listType: ListType): Flow<Resource<MoviePageResult>> {
            return when (listType) {
                ListType.MOST_POPULAR_DESC -> moviesRepository.fetchMostPopularDescending()
                ListType.MOST_POPULAR_ASC -> TODO()
                ListType.TOP_RATED_DESC -> TODO()
                ListType.TOP_RATED_ASC -> TODO()
                ListType.RELEASE_DATE_DESC -> TODO()
                ListType.RELEASE_DATE_ASC -> TODO()
                ListType.ALPHABETICAL_A_Z -> TODO()
                ListType.ALPHABETICAL_Z_A -> TODO()
                ListType.UPCOMING -> TODO()
            }
    }
}