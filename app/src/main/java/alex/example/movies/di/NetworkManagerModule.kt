package alex.example.movies.di

import alex.example.movies.services.NetworkRequestManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkManagerModule {

    @Provides
    @Singleton
    fun provideNetworkRequestManager(): NetworkRequestManager {
        return NetworkRequestManager
    }
}