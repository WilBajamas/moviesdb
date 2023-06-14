package alex.example.movies.data.repositories

import alex.example.movies.data.model.People
import alex.example.movies.data.remote.api.PeopleApi
import alex.example.movies.data.remote.datasource.PeopleRemotePagingSource
import alex.example.movies.data.remote.datasource.TrendingPeopleDataSource
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PeopleRepository @Inject constructor(
    private val peopleDataSource: TrendingPeopleDataSource,
    private val requestManager: NetworkRequestManager,
    private val dispatcher: DispatcherProvider,
    private val peopleApi: PeopleApi
) {

    private var searchQuery: String? = null

    // Paging Data
    private val pagingConfig = PagingConfig(
        pageSize = 100, prefetchDistance = 20, initialLoadSize = 160, enablePlaceholders = false
    )

    private val flow = Pager(
        pagingConfig
    ) {
        PeopleRemotePagingSource(requestManager, dispatcher, peopleApi, searchQuery)
    }.flow

    suspend fun fetchTrendingPeople(timeWindow: String) =
        peopleDataSource.fetchTrendingPeople(timeWindow)

    fun fetchPopularPeople(): Flow<PagingData<People>> {
        this.searchQuery = null
        return flow
    }

    fun fetchSearchPeople(searchQuery: String?): Flow<PagingData<People>> {
        this.searchQuery = searchQuery
        return flow
    }

}