package alex.example.movies.ui.viewmodels.maincontent

import alex.example.movies.data.model.MoviePageResult
import alex.example.movies.domain.model.ListType
import alex.example.movies.domain.use_case.movies.MoviesUseCase
import alex.example.movies.utils.Resource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesFragmentViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase
) : ViewModel() {

    private val _moviesState: MutableStateFlow<Resource<MoviePageResult>> = MutableStateFlow(Resource.Loading())
    val moviesState = _moviesState.asStateFlow()

    fun init () {
        viewModelScope.launch {
            val result = moviesUseCase(ListType.MOST_POPULAR_DESC).first()
            _moviesState.emit(result)
        }
    }

}