package alex.example.movies.data.remote.datasource

import alex.example.movies.data.model.FilmDetail
import alex.example.movies.data.remote.api.FilmDetailApi
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import alex.example.movies.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailDataSource @Inject constructor(
    private val filmDetailApi: FilmDetailApi,
    private val networkManager: NetworkRequestManager,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend fun fetchMovieDetails(id: Int): Flow<Resource<FilmDetail>> = flow {
        val result = withContext(dispatcherProvider.io) {
            networkManager.callApi {
                filmDetailApi.fetchMovieDetail(id)
            }
        }

        emit(result)
    }

}