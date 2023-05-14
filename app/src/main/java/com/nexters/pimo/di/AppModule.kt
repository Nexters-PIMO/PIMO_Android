package com.nexters.pimo.di

import android.content.Context
import android.util.Log
import com.nexters.pimo.BuildConfig
import com.nexters.pimo.remote.api.AuthenticationListener
import com.nexters.pimo.remote.api.BaseUrl
import com.nexters.pimo.remote.api.Interceptors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    @Provides
    fun provideBaseUrl(): BaseUrl = if (BuildConfig.DEBUG) {
        BaseUrl("https://fimo.k-net.kr/api/v1/")
    } else {
        BaseUrl("https://fimo.k-net.kr/api/v1/")
    }

    @Provides
    fun provideInterceptors(): Interceptors {
        return if (BuildConfig.DEBUG) {
            val logger = HttpLoggingInterceptor.Logger { message ->
                Log.d("OkHttp", message)
            }
            val loggingInterceptor = HttpLoggingInterceptor(logger)
                .apply { level = HttpLoggingInterceptor.Level.BODY }
            Interceptors(
                listOf(loggingInterceptor),
                emptyList()
            )
        } else {
            Interceptors.EMPTY
        }
    }

    @Provides
    fun provideAuthenticationListener(
        @ApplicationContext context: Context,
        //dataStore: DataStore<Preferences>,
    ): AuthenticationListener = object : AuthenticationListener {
        override fun onSessionExpired() {
            //sharedPreferences.edit { clear() }
            //SplashActivity.startActivity(context)
        }
    }
}
