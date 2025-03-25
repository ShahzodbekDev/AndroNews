package com.example.andronews.domain.repo

import com.example.andronews.domain.model.User

interface AuthRepository {
    suspend fun signIn(username: String, password: String): User
}