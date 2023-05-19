package alex.example.movies.ui.viewmodels.maincontent

import alex.example.movies.data.model.MoviePageResult
import alex.example.movies.domain.model.Languages
import alex.example.movies.domain.model.ListType
import alex.example.movies.domain.use_case.movies.MoviesUseCase
import alex.example.movies.ui.model.FilterRequest
import alex.example.movies.utils.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.chip.Chip
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesFragmentViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase
) : ViewModel() {

    // Movies list response result
    private val _moviesState: MutableStateFlow<Resource<MoviePageResult>> =
        MutableStateFlow(Resource.Loading())
    val moviesState = _moviesState.asStateFlow()

    val displayNames = ListType.values().map { it.displayName }
    val displayLanguages = Languages.values().map { it.displayName }

    val chipMap = mutableMapOf<Int, Chip>()

    private var listType = MutableLiveData(ListType.MOST_POPULAR_DESC)
    val listTypeData: LiveData<ListType>
        get(): LiveData<ListType> = listType

    private val genres = MutableLiveData<List<Int>>()
    val genresData: LiveData<List<Int>>
        get() = genres

    private val language = MutableLiveData(Languages.ENGLISH)
    val languageData: LiveData<Languages>
        get(): LiveData<Languages> = language

    private val userScoreMin = MutableLiveData(0f)
    val userScoreMinData: LiveData<Float>
        get(): LiveData<Float> = userScoreMin

    private val userScoreMax = MutableLiveData(100f)
    val userScoreMaxData: LiveData<Float>
        get(): LiveData<Float> = userScoreMax

    fun callMoviesApi() {
        viewModelScope.launch {
            val filterRequest = FilterRequest(
                listType.value!!,
                genres.value,
                language.value!!.iso639Id,
                userScoreMin.value!!,
                userScoreMax.value!!
            )
            val result = moviesUseCase(filterRequest).first()
            _moviesState.emit(result)
        }
    }

    fun setGenres(genres: List<Int>) {
        this.genres.value = genres
    }

    fun setListType(listType: ListType) {
        this.listType.value = listType
    }

    fun setLanguage(language: Languages) {
        this.language.value = language
    }

    fun setUserScoreMin(minScore: Float) {
        userScoreMin.value = minScore
    }

    fun setUserScoreMax(maxScore: Float) {
        userScoreMax.value = maxScore
    }
}
