package alex.example.movies.data.repositories

import alex.example.movies.data.model.Film
import alex.example.movies.data.remote.api.TvShowsListApi
import alex.example.movies.data.remote.datasource.TrendingTvShowsDataSource
import alex.example.movies.data.remote.datasource.TvShowsRemotePagingSource
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.ui.model.FilterRequest
import alex.example.movies.utils.DispatcherProvider
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvShowsRepository @Inject constructor(
    private val tvShowsListApi: TvShowsListApi,
    private val requestManager: NetworkRequestManager,
    private val dispatcher: DispatcherProvider,
    private val tvShowsDataSource: TrendingTvShowsDataSource
) {

    private lateinit var filterRequest: FilterRequest

    // Paging Data
    private val pagingConfig = PagingConfig(
        pageSize = 100, prefetchDistance = 20, initialLoadSize = 160, enablePlaceholders = false
    )

    private val flow = Pager(
        pagingConfig
    ) {
        TvShowsRemotePagingSource(tvShowsListApi, requestManager, dispatcher, filterRequest)
    }.flow

    fun fetchTvShows(
        filterRequest: FilterRequest
    ): Flow<PagingData<Film>> {
        this.filterRequest = filterRequest
        return flow
    }

    suspend fun fetchTrendingTvShows(timeWindow: String) =
        tvShowsDataSource.fetchTrendingTvShows(timeWindow)

}
