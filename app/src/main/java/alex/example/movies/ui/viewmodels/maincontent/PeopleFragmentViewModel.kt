package alex.example.movies.ui.viewmodels.maincontent

import alex.example.movies.data.model.People
import alex.example.movies.domain.use_case.people.PeopleUseCase
import alex.example.movies.domain.use_case.people.SearchPeopleUseCase
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleFragmentViewModel @Inject constructor(
    private val peopleUseCase: PeopleUseCase,
    private val searchPeopleUseCase: SearchPeopleUseCase
) : ViewModel() {

    // People list response result
    private val _peopleState: MutableStateFlow<PagingData<People>?> = MutableStateFlow(null)
    val peopleState = _peopleState.asStateFlow()

    private val _searchPeopleState: MutableStateFlow<PagingData<People>?> = MutableStateFlow(null)
    val searchPeopleState = _searchPeopleState.asStateFlow()

    private val _searchQueryData: MutableLiveData<String?> = MutableLiveData(null)

    fun fetchPopularPeople() = viewModelScope.launch {
        peopleUseCase().cachedIn(viewModelScope).collectLatest {
            _peopleState.emit(it)
        }
    }

    fun setAndFetchSearchPeople(searchQuery: String) {
        _searchQueryData.value = searchQuery
        fetchSearchPeople()
    }

    fun fetchSearchPeople() = viewModelScope.launch {
        searchPeopleUseCase(_searchQueryData.value).cachedIn(viewModelScope).collectLatest {
            _searchPeopleState.emit(it)
        }
    }

}