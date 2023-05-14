package com.nexters.pimo.remote.api

import okhttp3.Interceptor

data class Interceptors(
    val interceptors: List<Interceptor>,
    val networkInterceptors: List<Interceptor>
) {

    companion object {
        val EMPTY = Interceptors(emptyList(), emptyList())
    }
}
