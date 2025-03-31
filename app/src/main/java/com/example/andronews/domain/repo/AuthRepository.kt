package com.example.andronews.domain.repo

interface AuthRepository {
    suspend fun signIn(username: String, password: String)
}