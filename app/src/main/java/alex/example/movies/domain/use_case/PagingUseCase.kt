package alex.example.movies.domain.use_case

import alex.example.movies.ui.model.FilterRequest
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface PagingUseCase<T: Any> {
    operator fun invoke(filterRequest: FilterRequest): Flow<PagingData<T>>
}