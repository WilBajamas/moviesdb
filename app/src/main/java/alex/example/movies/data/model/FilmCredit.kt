package alex.example.movies.data.model

data class FilmCredit (
        val id: Int,
        val cast: List<Film>,
        val crew: List<Film>
        )