package alex.example.movies.data.model

data class Session(
    val expires_at: String,
    val request_token: String
): ApiResponse()

data class LoginRequest(
    val username: String,
    val password: String,
    val request_token: String
)
