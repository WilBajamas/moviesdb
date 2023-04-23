package alex.example.movies.data.state

sealed class AuthState<out R> {
    object Loading : AuthState<Nothing>()
    object ApiKeyBlank : AuthState<Nothing>()
    object SessionNull: AuthState<Nothing>()
    object SessionAvailable: AuthState<Nothing>()
}
