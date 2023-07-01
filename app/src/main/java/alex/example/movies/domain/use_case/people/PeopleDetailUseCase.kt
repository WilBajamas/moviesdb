package alex.example.movies.domain.use_case.people

import alex.example.movies.data.model.People
import alex.example.movies.data.repositories.PeopleRepository
import alex.example.movies.utils.Resource
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class PeopleDetailUseCase @Inject constructor(
    private val peopleRepository: PeopleRepository
) {

    suspend operator fun invoke(id: Int): Flow<Resource<People>> =
        peopleRepository.fetchPersonDetails(id)
}