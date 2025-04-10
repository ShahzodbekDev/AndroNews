package com.example.andronews.data.api.auth

import com.example.andronews.data.api.auth.dto.SignInRequest
import com.example.andronews.data.api.auth.dto.AuthResponse
import com.example.andronews.data.api.auth.dto.ForgotPasswordRequest
import com.example.andronews.data.api.auth.dto.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/sign-in")
    suspend fun signIn(@Body request: SignInRequest) : AuthResponse

    @POST("auth/sign-up")
    suspend fun signUp(@Body request: SignUpRequest) : AuthResponse

    @POST("auth/forgot-password")
    suspend fun forgotPassword(@Body request: ForgotPasswordRequest) : AuthResponse


}