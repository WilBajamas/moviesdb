package alex.example.movies.di.credits

import alex.example.movies.data.remote.api.CreditsApi
import alex.example.movies.services.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CreditsApiModule {

    @Provides
    @Singleton
    fun provideCreditsApi(
        retrofitInstance: RetrofitInstance
    ): CreditsApi {
        return retrofitInstance.retrofit().create(CreditsApi::class.java)
    }

}