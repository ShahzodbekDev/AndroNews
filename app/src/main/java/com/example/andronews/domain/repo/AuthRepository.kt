package com.example.andronews.domain.repo

import com.example.andronews.domain.model.Destination
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signIn(username: String, password: String)

    suspend fun signUp(username: String, email: String, password: String)

    suspend fun forgotPassword(email: String)

    fun destinationFlow() : Flow<Destination>

    suspend fun splashed()
}