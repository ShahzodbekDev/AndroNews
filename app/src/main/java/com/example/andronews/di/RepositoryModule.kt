package com.example.andronews.di

import com.example.andronews.data.repo.AuthRepositoryImpl
import com.example.andronews.data.repo.NewsRepositoryImpl
import com.example.andronews.domain.repo.AuthRepository
import com.example.andronews.domain.repo.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun bindNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository
}