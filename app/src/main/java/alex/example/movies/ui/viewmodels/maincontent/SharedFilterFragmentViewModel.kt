package alex.example.movies.ui.viewmodels.maincontent

import alex.example.movies.domain.model.Languages
import alex.example.movies.domain.model.ListType
import alex.example.movies.domain.use_case.PagingUseCase
import alex.example.movies.ui.model.FilterRequest
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.google.android.material.chip.Chip
import kotlinx.coroutines.flow.Flow


open class SharedFilterFragmentViewModel<T : Any> : ViewModel() {

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

    fun callPagingRemoteAction(
        pagingUseCase: PagingUseCase<T>, filterRequest: FilterRequest
    ): Flow<PagingData<T>> = pagingUseCase.invoke(filterRequest).cachedIn(viewModelScope)

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
