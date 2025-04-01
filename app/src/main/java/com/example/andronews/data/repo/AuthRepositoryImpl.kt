package com.example.andronews.data.repo

import com.example.andronews.data.api.auth.AuthApi
import com.example.andronews.data.api.auth.dto.AuthResponse
import com.example.andronews.data.api.auth.dto.SignInRequest
import com.example.andronews.data.api.auth.dto.SignUpRequest
import com.example.andronews.data.store.TokenStore
import com.example.andronews.data.store.UserStore
import com.example.andronews.domain.repo.AuthRepository
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val tokenStore: TokenStore,
    private val userStore: UserStore
) : AuthRepository {
    override suspend fun signIn(username: String, password: String) {
        val request = SignInRequest(username, password)
        val response = authApi.signIn(request)
        saveUserInfo(response)
    }

    override suspend fun signUp(username: String,email:String,password: String){
        val requset = SignUpRequest(username,email,password)
        val response = authApi.signUp(requset)
        saveUserInfo(response)
    }

    private suspend fun saveUserInfo(response: AuthResponse){
        tokenStore.set(response.token)
        userStore.set(response.user)
    }

}