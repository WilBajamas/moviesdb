package alex.example.movies.data.remote

import alex.example.movies.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    private val baseUrl = "https://api.themoviedb.org/3/"
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient =
        OkHttpClient.Builder().addInterceptor(AuthInterceptor()).addInterceptor(loggingInterceptor)
            .build()

    fun retrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                okHttpClient
            ).build()
    }

    inner class AuthInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val originalUrl = originalRequest.url

            val newUrl =
                originalUrl.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()
            val requestBuilder = originalRequest.newBuilder().url(newUrl)
            val request = requestBuilder.build()
            return chain.proceed(request)
        }

    }
}
