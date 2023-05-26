package alex.example.movies.domain.use_case.movies

import alex.example.movies.data.model.Movie
import alex.example.movies.data.repositories.MoviesRepository
import alex.example.movies.ui.model.FilterRequest
import androidx.paging.PagingData
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

    operator fun invoke(
        filterRequest: FilterRequest
    ): Flow<PagingData<Movie>> {
        return moviesRepository.fetchMovies(filterRequest)
    }
}
