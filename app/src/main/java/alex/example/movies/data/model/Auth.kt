package alex.example.movies.data.model

data class Session(
    val success: Boolean,
    val expires_at: String,
    val request_token: String
)

data class LoginRequest(
    val username: String,
    val password: String,
    val request_token: String
)
