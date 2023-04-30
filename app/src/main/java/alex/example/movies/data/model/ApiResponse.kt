package alex.example.movies.data.model

 open class ApiResponse(
    val success: Boolean = false,
    val status_code: Int? = null,
    val status_message: String? = null
)