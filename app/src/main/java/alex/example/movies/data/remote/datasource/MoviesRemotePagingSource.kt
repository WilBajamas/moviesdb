package alex.example.movies.data.remote.datasource

import alex.example.movies.data.model.Movie
import alex.example.movies.data.remote.api.MoviesListApi
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.ui.model.FilterRequest
import alex.example.movies.utils.Const
import alex.example.movies.utils.DispatcherProvider
import alex.example.movies.utils.Resource
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.IOException

class MoviesRemotePagingSource (
    private val moviesListApi: MoviesListApi,
    private val requestManager: NetworkRequestManager,
    private val dispatcher: DispatcherProvider,
    private val filterRequest: FilterRequest
) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        try {
            val page = params.key ?: Const.FIRST_PAGE_INDEX
            delay(7000L)
            val apiResult = withContext(dispatcher.io) {
                requestManager.callApi {
                    moviesListApi.fetchMoviesWithQuery(
                        page = page,
                        sortBy = filterRequest.sortBy.sortByListType,
                        withGenres = filterRequest.genres?.joinToString(","),
                        withOriginalLanguage = filterRequest.languageId,
                        userScoreMinimum = filterRequest.userScoreMin / 10,
                        userScoreMaximum = filterRequest.userScoreMax / 10
                    )
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
                        data = apiResult.data.results,
                        prevKey = null,
                        nextKey = nextPage
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

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}

// [Deprecated]
 /* class MoviesRemoteDataSource @Inject constructor(
    private val moviesListApi: MoviesListApi,
    private val requestManager: NetworkRequestManager,
    private val dispatcher: DispatcherProvider
) {

    // Descending by default
    suspend fun fetchMovies(
        page: Int = 1, filterRequest: FilterRequest
    ): Flow<Resource<MoviePageResult>> = flow {
        val result: Resource<MoviePageResult> = withContext(dispatcher.io) {
            requestManager.callApi {
                moviesListApi.fetchMoviesWithQuery(
                    page = page,
                    sortBy = filterRequest.sortBy.sortByListType,
                    withGenres = filterRequest.genres?.joinToString(","),
                    withOriginalLanguage = filterRequest.languageId,
                    userScoreMinimum = filterRequest.userScoreMin / 10,
                    userScoreMaximum = filterRequest.userScoreMax / 10
                )
            }
        }
        emit(result)
    }
} */
