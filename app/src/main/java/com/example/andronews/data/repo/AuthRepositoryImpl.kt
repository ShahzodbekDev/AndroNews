package com.example.andronews.data.repo

import android.util.Log
import com.example.andronews.data.api.auth.AuthApi
import com.example.andronews.data.api.auth.dto.AuthResponse
import com.example.andronews.data.api.auth.dto.ForgotPasswordRequest
import com.example.andronews.data.api.auth.dto.SignInRequest
import com.example.andronews.data.api.auth.dto.SignUpRequest
import com.example.andronews.data.store.SplashedStore
import com.example.andronews.data.store.TokenStore
import com.example.andronews.data.store.UserStore
import com.example.andronews.domain.model.Destination
import com.example.andronews.domain.repo.AuthRepository
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val tokenStore: TokenStore,
    private val userStore: UserStore,
    private val splashedStore: SplashedStore
) : AuthRepository {
    override suspend fun signIn(username: String, password: String) {
        val request = SignInRequest(username, password)
        val response = authApi.signIn(request)
        saveUserInfo(response)
    }

    override suspend fun signUp(username: String, email: String, password: String) {
        val request = SignUpRequest(username, email, password)
        val response = authApi.signUp(request)
        saveUserInfo(response)
    }

    override suspend fun forgotPassword(email: String) {
        val request = ForgotPasswordRequest(email)
        authApi.forgotPassword(request)
    }

    override fun destinationFlow() = channelFlow {
        suspend fun sendDestination() {
            when {
                tokenStore.get() != null -> send(Destination.Home)
                splashedStore.get() == true -> send(Destination.SignIn)
                else -> send(Destination.Splash)

            }
        }

        launch {
            tokenStore.getFlow().collectLatest {
                sendDestination()
            }
        }

        launch {
            splashedStore.getFlow().collectLatest {
                sendDestination()
            }
        }

    }

    override suspend fun splashed() = splashedStore.set(true)


    private suspend fun saveUserInfo(response: AuthResponse) {
        tokenStore.set(response.token)
        userStore.set(response.user)
    }

}