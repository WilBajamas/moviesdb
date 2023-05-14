package alex.example.movies.ui.viewmodels.filter

import alex.example.movies.domain.model.Languages
import alex.example.movies.domain.model.ListType
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FilterFragmentViewModel : ViewModel() {

    val displayNames = ListType.values().map { it.displayName }
    val displayLanguages = Languages.values().map { it.displayName }

    private val _listType = MutableLiveData(ListType.MOST_POPULAR_DESC)
    val listType: LiveData<ListType>
        get() = _listType

    private val _genres = MutableLiveData<List<Int>>()
    val genres: LiveData<List<Int>>
        get() = _genres

    private val _language = MutableLiveData(Languages.ENGLISH)
    val language: LiveData<Languages>
        get() = _language

    private val _userScoreMin = MutableLiveData(20f)
    val userScoreMin: LiveData<Float>
        get() = _userScoreMin

    private val _userScoreMax = MutableLiveData(70f)
    val userScoreMax: LiveData<Float>
        get() = _userScoreMax


    fun setGenres(genres: List<Int>) {
        _genres.value = genres
    }

    fun setListType(listType: ListType) {
        _listType.value = listType
    }

    fun setLanguage(language: Languages) {
        _language.value = language
    }

    fun setUserScoreMin(minScore: Float) {
        _userScoreMin.value = minScore
    }

    fun setUserScoreMax(maxScore: Float) {
        _userScoreMax.value = maxScore
    }
}