package alex.example.movies.di.auth

import alex.example.movies.data.AuthApi
import alex.example.movies.data.remote.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthApiModule{

    @Provides
    @Singleton
    fun provideAuthApi(
        retrofitInstance: RetrofitInstance
    ): AuthApi {
        return retrofitInstance.retrofit().create(AuthApi::class.java)
    }

}