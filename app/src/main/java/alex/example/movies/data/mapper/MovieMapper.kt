package alex.example.movies.data.mapper

import alex.example.movies.data.model.Movie
import alex.example.movies.data.model.MoviePageResult
import alex.example.movies.domain.model.MovieDomain
import alex.example.movies.domain.model.MoviePageResultDomain

class MovieMapper {

    fun MoviePageResult.toDomain(moviePageResult: MoviePageResult) = MoviePageResultDomain(
        moviePageResult.page, resultsToDomain(moviePageResult.results), total_results, total_pages
    )

    private fun resultsToDomain(results: List<Movie>): List<MovieDomain> {
        return results.map { it.toDomain(it) }
    }

    private fun Movie.toDomain(movie: Movie) = MovieDomain(
        poster_path,
        adult,
        overview,
        release_date,
        genre_ids,
        id,
        original_title,
        original_language,
        title,
        backdrop_path,
        popularity,
        vote_count,
        video,
        vote_average
    )
}