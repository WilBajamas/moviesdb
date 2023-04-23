package alex.example.movies.di

import alex.example.movies.data.remote.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitInstanceModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): RetrofitInstance {
        return RetrofitInstance()
    }
}