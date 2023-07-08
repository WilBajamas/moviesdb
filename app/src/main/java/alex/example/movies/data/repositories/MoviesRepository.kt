package alex.example.movies.data.repositories

import alex.example.movies.data.local.dao.FilmDao
import alex.example.movies.data.remote.datasource.TrendingMoviesDataSource
import alex.example.movies.data.model.Film
import alex.example.movies.data.remote.api.MoviesListApi
import alex.example.movies.data.remote.datasource.MoviesRemotePagingSource
import alex.example.movies.domain.model.FilmType
import alex.example.movies.services.NetworkRequestManager
import alex.example.movies.ui.model.FilterRequest
import alex.example.movies.utils.DispatcherProvider
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesListApi: MoviesListApi,
    private val requestManager: NetworkRequestManager,
    private val dispatcher: DispatcherProvider,
    private val trendingMoviesDataSource: TrendingMoviesDataSource,
    private val filmDao: FilmDao,
) {

    private lateinit var filterRequest: FilterRequest

    // Paging Data
    private val pagingConfig = PagingConfig(
        pageSize = 100, prefetchDistance = 20, initialLoadSize = 160, enablePlaceholders = false
    )

    private val flow = Pager(
        pagingConfig
    ) {
        MoviesRemotePagingSource(moviesListApi, requestManager, dispatcher, filterRequest)
    }.flow

    fun fetchMovies(
        filterRequest: FilterRequest
    ): Flow<PagingData<Film>> {
        this.filterRequest = filterRequest
        return flow
    }


    suspend fun fetchTrendingMovies(
        timeWindow: String
    ) = trendingMoviesDataSource.fetchTrendingMovies(
        timeWindow
    )

    // TODO: Might need a datasource layer
    suspend fun fetchFavouriteMovies() = filmDao.getAllFilmsByType(FilmType.MOVIE.ordinal)

}
