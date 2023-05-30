package alex.example.movies.data.model

data class Backdrop(
    val file_path: String
)

data class BackdropResponse(
    val id: Int,
    val backdrops: List<Backdrop>
)
