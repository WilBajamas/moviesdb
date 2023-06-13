package alex.example.movies.ui.viewmodels.maincontent

import alex.example.movies.data.model.Film
import alex.example.movies.domain.use_case.movies.MoviesUseCase
import alex.example.movies.ui.model.FilterRequest
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesFragmentViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase
) : SharedFilterFragmentViewModel<Film>() {

    // Movies list response result
    private val _moviesState: MutableStateFlow<PagingData<Film>?> = MutableStateFlow(null)
    val moviesState = _moviesState.asStateFlow()

    private val _filterLiveData = MutableLiveData(
        FilterRequest(
            listTypeData.value!!,
            genresData.value,
            languageData.value!!.iso639Id,
            userScoreMinData.value!!,
            userScoreMaxData.value!!
        )
    )

    fun callMoviesApi(
        filterRequest: FilterRequest = _filterLiveData.value!!
    ) {
        viewModelScope.launch {
            callPagingRemoteAction(moviesUseCase, filterRequest).collectLatest {
                _moviesState.emit(it)
            }
        }
    }

    fun setFilter(filterRequest: FilterRequest) {
        _filterLiveData.value = filterRequest
        callMoviesApi(filterRequest)
    }
}