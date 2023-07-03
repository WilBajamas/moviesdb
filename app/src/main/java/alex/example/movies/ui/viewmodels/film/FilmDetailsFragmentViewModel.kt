package alex.example.movies.ui.viewmodels.film

import alex.example.movies.data.model.Credit
import alex.example.movies.data.model.FilmDetail
import alex.example.movies.domain.use_case.credit.CreditsUseCase
import alex.example.movies.domain.use_case.film.FilmDetailsUseCase
import alex.example.movies.utils.Resource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmDetailsFragmentViewModel @Inject constructor(
    private val filmDetailsUseCase: FilmDetailsUseCase,
    private val creditsUseCase: CreditsUseCase
) : ViewModel() {

    private val _filmDetailsStateFlow = MutableStateFlow<Resource<FilmDetail>>(Resource.Loading())
    val filmDetailsStateFlow: StateFlow<Resource<FilmDetail>>
        get() = _filmDetailsStateFlow

    private val _filmCreditsStateFlow = MutableStateFlow<Resource<Credit>>(Resource.Loading())
    val filmCreditsStateFlow: StateFlow<Resource<Credit>>
        get() = _filmCreditsStateFlow

    fun fetchFilmDetails(id: Int, filmType: String) {
        viewModelScope.launch {
            val filmDetailsResult = filmDetailsUseCase.invoke(id, filmType).lastOrNull()
            filmDetailsResult?.let {
                _filmDetailsStateFlow.emit(it)
            }
        }
    }

    fun fetchFilmCredits(id: Int, filmType: String) {
        viewModelScope.launch {
            val filmCreditsResult = creditsUseCase.fetchCredits(id, filmType).lastOrNull()
            filmCreditsResult?.let {
                _filmCreditsStateFlow.emit(it)
            }
        }
    }
}
