package alex.example.movies.domain.use_case.tvshows

import alex.example.movies.data.model.Film
import alex.example.movies.data.repositories.TvShowsRepository
import alex.example.movies.domain.use_case.PagingUseCase
import alex.example.movies.ui.model.FilterRequest
import androidx.paging.PagingData
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class TvShowsUseCase @Inject constructor(
    private val tvShowsRepository: TvShowsRepository,
) : PagingUseCase<Film> {

    override fun invoke(filterRequest: FilterRequest): Flow<PagingData<Film>> =
        tvShowsRepository.fetchTvShows(filterRequest)
}
