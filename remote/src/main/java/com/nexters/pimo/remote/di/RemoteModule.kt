package com.nexters.pimo.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RemoteModule {

    @Provides
    @Singleton
    @Named("BaseUrl")
    fun provideBaseUrl(): String = "https://dummyjson.com/"

    @Provides
    @Singleton
    fun provideHttpClient(@Named("BaseUrl") baseUrl: String) = HttpClient(CIO) {

        defaultRequest {
            url(baseUrl)
            accept(ContentType.Application.Json)
            contentType(ContentType.Application.Json)
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }

        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.ALL
        }
    }
}
