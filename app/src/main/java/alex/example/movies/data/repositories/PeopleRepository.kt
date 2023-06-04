package alex.example.movies.data.repositories

import alex.example.movies.data.remote.datasource.TrendingPeopleDataSource
import javax.inject.Inject

class PeopleRepository @Inject constructor(
    private val peopleDataSource: TrendingPeopleDataSource
) {

    suspend fun fetchTrendingPeople(timeWindow: String) =
        peopleDataSource.fetchTrendingPeople(timeWindow)

}