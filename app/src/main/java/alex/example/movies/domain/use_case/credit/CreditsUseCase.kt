package alex.example.movies.domain.use_case.credit

import alex.example.movies.data.repositories.CreditsRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class CreditsUseCase @Inject constructor(
    private val creditsRepository: CreditsRepository
){

    fun fetchCredits(id: Int, filmType: String) = creditsRepository.fetchCredits(id, filmType)

}