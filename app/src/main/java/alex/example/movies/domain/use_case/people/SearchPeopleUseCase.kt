package alex.example.movies.domain.use_case.people

import alex.example.movies.data.model.People
import alex.example.movies.data.repositories.PeopleRepository
import androidx.paging.PagingData
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class SearchPeopleUseCase @Inject constructor(
    private val peopleRepository: PeopleRepository
) {

    operator fun invoke(searchQuery: String?): Flow<PagingData<People>> =
        peopleRepository.fetchSearchPeople(searchQuery)
}