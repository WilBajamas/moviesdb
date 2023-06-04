package alex.example.movies.data.remote.datasource

import alex.example.movies.data.model.PeopleResponse
import alex.example.movies.data.remote.api.PeopleApi
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import alex.example.movies.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrendingPeopleDataSource @Inject constructor(
    private val peopleApi: PeopleApi,
    private val requestManager: NetworkRequestManager,
    private val dispatcher: DispatcherProvider
) {

    suspend fun fetchTrendingPeople(
        timeWindow: String
    ): Flow<Resource<PeopleResponse>> = flow {
        val result: Resource<PeopleResponse> = withContext(dispatcher.io) {
            requestManager.callApi {
                peopleApi.fetchTrendingPeopleWithTimeWindow(
                    timeWindow
                )
            }
        }
        emit(result)
    }

}