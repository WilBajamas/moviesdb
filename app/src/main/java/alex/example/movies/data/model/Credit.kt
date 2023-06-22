package alex.example.movies.data.model

data class Credit(
    val id: Int,
    val cast: List<People>,
    val crew: List<People>
)
