package alex.example.movies.data.remote.datasource

import alex.example.movies.data.model.FilmCredit
import alex.example.movies.data.model.People
import alex.example.movies.data.remote.api.PeopleApi
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import alex.example.movies.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PeopleDetailDataSource @Inject constructor(
    private val peopleApi: PeopleApi,
    private val networkManager: NetworkRequestManager,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend fun fetchPeopleDetails(id: Int): Flow<Resource<People>> = flow {
        val result = withContext(dispatcherProvider.io) {
            networkManager.callApi {
                peopleApi.fetchPersonDetails(id)
            }
        }

        emit(result)
    }

    suspend fun fetchPersonMovieCredits(id: Int): Flow<Resource<FilmCredit>> = flow {
        val result = withContext(dispatcherProvider.io) {
            networkManager.callApi {
                peopleApi.fetchPersonMovieCredits(id)
            }
        }

        emit(result)
    }

}