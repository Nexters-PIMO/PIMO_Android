package com.nexters.pimo.remote.api

import okhttp3.Interceptor
import okhttp3.Response

class ImgurInterceptor(
    private val clientId: ClientId
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val request = request()
            .newBuilder()
            .addHeader(HEADER_KEY, "Client-ID $clientId")
            .build()
        proceed(request)
    }

    companion object {
        private const val HEADER_KEY = "Authorization"
    }
}
