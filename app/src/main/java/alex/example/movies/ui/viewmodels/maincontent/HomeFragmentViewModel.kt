package alex.example.movies.ui.viewmodels.maincontent

import alex.example.movies.data.model.Backdrop
import alex.example.movies.data.model.MoviePageResult
import alex.example.movies.data.repositories.ImageBackdropRepository
import alex.example.movies.data.repositories.MoviesRepository
import alex.example.movies.ui.model.TimeWindow
import alex.example.movies.utils.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val imageBackdropRepository: ImageBackdropRepository,
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _backdropCollectionLiveData = MutableLiveData<List<Backdrop>?>()
    val backdropData: LiveData<List<Backdrop>?>
        get(): LiveData<List<Backdrop>?> = _backdropCollectionLiveData

    private val _trendingMovies = MutableStateFlow<Resource<MoviePageResult>>(Resource.Loading())
    val trendingMovies: StateFlow<Resource<MoviePageResult>>
        get() = _trendingMovies


    fun init() {
        viewModelScope.launch {
            when (val backdropResource = imageBackdropRepository.fetchImageBackdropCollection()) {
                is Resource.Success -> {
                    _backdropCollectionLiveData.value = backdropResource.data?.backdrops
                }
                else -> _backdropCollectionLiveData.value = null
            }

            _trendingMovies.emit(moviesRepository.fetchTrendingMovies(TimeWindow.TODAY.timeFrame).last())

        }
    }

}