package com.nexters.pimo.remote.di

import com.nexters.pimo.data.provider.AccessTokenProvider
import com.nexters.pimo.data.provider.RefreshTokenProvider
import com.nexters.pimo.remote.api.AccessTokenInterceptor
import com.nexters.pimo.remote.api.ApiService
import com.nexters.pimo.remote.api.AuthenticationListener
import com.nexters.pimo.remote.api.Authenticator
import com.nexters.pimo.remote.api.BaseUrl
import com.nexters.pimo.remote.api.Interceptors
import com.nexters.pimo.remote.api.RefreshApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RemoteModule {

    @Provides
    @Singleton
    fun provideApiService(
        baseUrl: BaseUrl,
        interceptors: Interceptors,
        accessTokenProvider: AccessTokenProvider,
        refreshTokenProvider: RefreshTokenProvider,
        authenticationListener: AuthenticationListener,
    ): ApiService {
        val authenticator = Authenticator(
            apiService = provideRefreshApiService(baseUrl, interceptors),
            accessTokenProvider = accessTokenProvider,
            refreshTokenProvider = refreshTokenProvider,
            authenticationListener = authenticationListener
        )

        return Retrofit.Builder()
            .baseUrl(baseUrl.value)
            .client(
                createOkHttpClient(interceptors) {
                    addInterceptor(AccessTokenInterceptor(accessTokenProvider))
                    authenticator(authenticator)
                }
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    private fun provideRefreshApiService(
        baseUrl: BaseUrl,
        interceptors: Interceptors,
    ): RefreshApiService = Retrofit.Builder()
        .baseUrl(baseUrl.value)
        .client(createOkHttpClient(interceptors))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RefreshApiService::class.java)

    private fun createOkHttpClient(
        interceptors: Interceptors,
        apply: OkHttpClient.Builder.() -> Unit = { },
    ) = OkHttpClient.Builder()
        .apply {
            interceptors.interceptors.forEach(::addInterceptor)
            interceptors.networkInterceptors.forEach(::addNetworkInterceptor)
        }
        .apply(apply)
        .build()
}
