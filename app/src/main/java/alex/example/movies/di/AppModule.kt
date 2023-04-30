package alex.example.movies.di

import alex.example.movies.utils.Const.SHARED_PREFERENCE_NAME
import alex.example.movies.utils.DefaultDispatcher
import alex.example.movies.utils.DispatcherProvider
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider{
        return DefaultDispatcher()
    }
}