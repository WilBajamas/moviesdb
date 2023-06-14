package alex.example.movies.data.remote.datasource

import alex.example.movies.data.model.People
import alex.example.movies.data.remote.api.PeopleApi
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.utils.Const
import alex.example.movies.utils.DispatcherProvider
import alex.example.movies.utils.Resource
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class PeopleRemotePagingSource @Inject constructor(
    private val requestManager: NetworkRequestManager,
    private val dispatcher: DispatcherProvider,
    private val peopleApi: PeopleApi
) : PagingSource<Int, People>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, People> {
        try {
            val page = params.key ?: Const.FIRST_PAGE_INDEX
            val apiResult = withContext(dispatcher.io) {
                requestManager.callApi {
                    peopleApi.fetchPopularPeople(page)
                }
            }

            return when (apiResult) {
                is Resource.Success -> {
                    val resultPage = apiResult.data!!.page
                    val totalPage = apiResult.data.total_pages
                    val nextPage = if (resultPage < totalPage) {
                        resultPage + 1
                    } else {
                        null
                    }
                    LoadResult.Page(
                        data = apiResult.data.results, prevKey = null, nextKey = nextPage
                    )
                }
                is Resource.Error -> LoadResult.Error(IOException(apiResult.message))
                else -> TODO()
            }
        } catch (e: Exception) {
            // Unknown Error
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, People>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}