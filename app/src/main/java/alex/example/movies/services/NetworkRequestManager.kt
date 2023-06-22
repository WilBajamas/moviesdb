package alex.example.movies.services

import alex.example.movies.data.model.ApiResponse
import alex.example.movies.utils.Resource
import com.google.gson.Gson
import retrofit2.Response


// TODO: Find out why we can't use class
object NetworkRequestManager {
    suspend fun <T> callApi(apiCall: suspend () -> Response<T>): Resource<T> {
        return try {
            val response = apiCall.invoke()

            return if (response.isSuccessful) {
                Resource.Success(data = response.body())
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, ApiResponse::class.java)
                Resource.Error(message = errorResponse.status_message ?: "Unknown Error")
            }
        } catch (exception: Exception) {
            exception.let { Resource.Error(message = exception.message!!) }
        }
    }
}