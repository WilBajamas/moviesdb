package alex.example.movies.ui.viewmodels.maincontent

import alex.example.movies.data.model.People
import alex.example.movies.domain.use_case.people.PeopleUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleFragmentViewModel @Inject constructor(
    private val peopleUseCase: PeopleUseCase
) : ViewModel() {

    // People list response result
    private val _peopleState: MutableStateFlow<PagingData<People>?> = MutableStateFlow(null)
    val peopleState = _peopleState.asStateFlow()

    fun fetchPopularPeople() = viewModelScope.launch {
        peopleUseCase.invoke().collectLatest {
            _peopleState.emit(it)
        }
    }

}