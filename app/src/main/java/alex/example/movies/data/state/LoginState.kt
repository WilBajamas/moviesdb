package alex.example.movies.data.state

sealed class LoginState {
    class CreateRequestTokenSuccess<out T>(val sessionData: T) : AuthState<T>()
}