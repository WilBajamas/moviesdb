package alex.example.movies.data

import alex.example.movies.data.model.LoginRequest
import alex.example.movies.data.model.Session
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @GET("authentication/token/new")
    suspend fun createRequestToken(): Response<Session>

    @POST("authentication/token/validate_with_login")
    suspend fun createSessionWithLogin(
        @Body loginRequest: LoginRequest
    ): Response<Session>

}