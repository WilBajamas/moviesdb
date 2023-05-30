package alex.example.movies.ui.viewmodels.maincontent

import alex.example.movies.data.model.Backdrop
import alex.example.movies.data.repositories.ImageBackdropRepository
import alex.example.movies.utils.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val imageBackdropRepository: ImageBackdropRepository
) : ViewModel() {

    private val _backdropCollectionLiveData = MutableLiveData<List<Backdrop>?>()
    val backdropData: LiveData<List<Backdrop>?>
        get(): LiveData<List<Backdrop>?> = _backdropCollectionLiveData

    private val _sectionItemsLiveData = MutableLiveData<List<String>>()
    val sectionItemsData: LiveData<List<String>>
        get(): LiveData<List<String>> = _sectionItemsLiveData

    fun init() {

        val sampleSections: MutableList<String> = mutableListOf()
        for (i in 1..10) {
            sampleSections += "Sample"
        }
        _sectionItemsLiveData.value = sampleSections

        viewModelScope.launch {
            when (val backdropResource = imageBackdropRepository.fetchImageBackdropCollection()) {
                is Resource.Success -> {
                    _backdropCollectionLiveData.value = backdropResource.data?.backdrops
                }
                else -> _backdropCollectionLiveData.value = null
            }
        }
    }
}