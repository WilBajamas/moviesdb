package alex.example.movies.ui.viewmodels.film

import alex.example.movies.data.model.Credit
import alex.example.movies.data.model.FilmDetail
import alex.example.movies.domain.use_case.credit.CreditsUseCase
import alex.example.movies.domain.use_case.film.FavouriteFilmUseCase
import alex.example.movies.domain.use_case.film.FilmDetailsUseCase
import alex.example.movies.domain.use_case.film.LocalFilmUseCase
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
    private val creditsUseCase: CreditsUseCase,
    private val favouriteFilmUseCase: FavouriteFilmUseCase,
    private val localFilmUseCase: LocalFilmUseCase
) : ViewModel() {

    private val _filmDetailsStateFlow = MutableStateFlow<Resource<FilmDetail>>(Resource.Loading())
    val filmDetailsStateFlow: StateFlow<Resource<FilmDetail>>
        get() = _filmDetailsStateFlow

    private val _filmCreditsStateFlow = MutableStateFlow<Resource<Credit>>(Resource.Loading())
    val filmCreditsStateFlow: StateFlow<Resource<Credit>>
        get() = _filmCreditsStateFlow

    private val _filmFavouriteStatusStateFlow = MutableStateFlow(false)
    val filmFavouriteStatusStateFlow: StateFlow<Boolean>
        get() = _filmFavouriteStatusStateFlow

    private val _filmFavouriteStatusSharedFlow = MutableSharedFlow<Boolean>()
    val filmFavouriteStatusSharedFlow: SharedFlow<Boolean>
        get() = _filmFavouriteStatusSharedFlow

    fun fetchFilmDetails(id: Int, filmType: String) {
        viewModelScope.launch {
            localFilmUseCase(id)?.let {
                _filmDetailsStateFlow.emit(Resource.Success(it))
                _filmFavouriteStatusStateFlow.emit(true)

            } ?: run {

                _filmFavouriteStatusStateFlow.emit(false)
                val filmDetailsResult = filmDetailsUseCase(id, filmType).lastOrNull()
                filmDetailsResult?.let { filmDetail ->
                    _filmDetailsStateFlow.emit(filmDetail)
                }

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

    fun favouriteFilm(film: FilmDetail, filmType: String) {
        viewModelScope.launch {
            val result = favouriteFilmUseCase(film, filmType, _filmFavouriteStatusStateFlow.value).last()
            _filmFavouriteStatusStateFlow.emit(
                result)

            _filmFavouriteStatusSharedFlow.emit(result)
        }
    }
}
