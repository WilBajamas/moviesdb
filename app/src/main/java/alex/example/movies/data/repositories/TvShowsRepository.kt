package alex.example.movies.data.repositories

import alex.example.movies.data.remote.datasource.TrendingTvShowsDataSource
import javax.inject.Inject

class TvShowsRepository @Inject constructor(
    private val tvShowsDataSource: TrendingTvShowsDataSource
) {

    suspend fun fetchTrendingTvShows(timeWindow: String) =
        tvShowsDataSource.fetchTrendingTvShows(timeWindow)

}