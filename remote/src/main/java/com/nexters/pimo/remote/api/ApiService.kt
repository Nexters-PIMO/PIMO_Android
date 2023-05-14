package com.nexters.pimo.remote.api

import com.nexters.pimo.remote.model.BearerTokenResponse
import com.nexters.pimo.remote.model.LoginRequest
import com.nexters.pimo.remote.model.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): BearerTokenResponse

    @POST("auth/signup")
    suspend fun signup(@Body request: SignUpRequest)

    @POST("auth/logout")
    suspend fun logout()

    @GET("user/validate/nickname")
    suspend fun validateNickname(@Query("nickname") nickname: String): String

    @GET("user/validate/archive")
    suspend fun validateArchive(@Query("archive") archive: String): String
}
