package alex.example.movies.data.repositories

import alex.example.movies.data.remote.api.PeopleApi
import alex.example.movies.data.remote.datasource.PeopleRemotePagingSource
import alex.example.movies.data.remote.datasource.TrendingPeopleDataSource
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.DispatcherProvider
import androidx.paging.Pager
import androidx.paging.PagingConfig
import javax.inject.Inject

class PeopleRepository @Inject constructor(
    private val peopleDataSource: TrendingPeopleDataSource,
    private val requestManager: NetworkRequestManager,
    private val dispatcher: DispatcherProvider,
    private val peopleApi: PeopleApi
) {

    // Paging Data
    private val pagingConfig = PagingConfig(
        pageSize = 100, prefetchDistance = 20, initialLoadSize = 160, enablePlaceholders = false
    )

    private val flow = Pager(
        pagingConfig
    ) {
        PeopleRemotePagingSource(requestManager, dispatcher, peopleApi)
    }.flow

    suspend fun fetchTrendingPeople(timeWindow: String) =
        peopleDataSource.fetchTrendingPeople(timeWindow)

    fun fetchPopularPeople() = flow

}