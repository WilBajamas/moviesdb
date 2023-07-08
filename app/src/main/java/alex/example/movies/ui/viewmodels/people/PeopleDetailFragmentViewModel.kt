package alex.example.movies.ui.viewmodels.people

import alex.example.movies.data.model.FilmCredit
import alex.example.movies.data.model.People
import alex.example.movies.domain.use_case.people.PeopleDetailUseCase
import alex.example.movies.domain.use_case.people.PersonMovieCreditsUseCase
import alex.example.movies.utils.Resource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleDetailFragmentViewModel @Inject constructor(
    private val peopleDetailUseCase: PeopleDetailUseCase,
    private val peopleMovieCreditsUseCase: PersonMovieCreditsUseCase,
) : ViewModel() {

    private val _peopleDetailStateFlow: MutableStateFlow<Resource<People>> =
        MutableStateFlow(Resource.Loading())
    val peopleDetailStateFlow = _peopleDetailStateFlow.asStateFlow()

    private val _peopleMovieCreditsStateFlow: MutableStateFlow<Resource<FilmCredit>> =
        MutableStateFlow(Resource.Loading())
    val peopleMovieCreditsStateFlow = _peopleMovieCreditsStateFlow.asStateFlow()

    fun fetchDetails(id: Int) {
        viewModelScope.launch {
            _peopleDetailStateFlow.emit(peopleDetailUseCase(id).last())
        }
    }

    fun fetchMoviesCredits(id: Int) {
        viewModelScope.launch {
            _peopleMovieCreditsStateFlow.emit(peopleMovieCreditsUseCase(id).last())
        }
    }

}