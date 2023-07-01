package alex.example.movies.domain.use_case.people

import alex.example.movies.data.repositories.PeopleRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class PersonMovieCreditsUseCase @Inject constructor(
    private val peopleRepository: PeopleRepository
) {

    suspend operator fun invoke(id: Int) = peopleRepository.fetchPersonMovieCredits(id)

}