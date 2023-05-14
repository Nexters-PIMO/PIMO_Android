package com.nexters.pimo.remote.api

import com.nexters.pimo.remote.model.BearerTokenRequest
import com.nexters.pimo.remote.model.BearerTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RefreshApiService {

    @POST("auth/reissue")
    suspend fun refresh(@Body request: BearerTokenRequest): BearerTokenResponse
}
