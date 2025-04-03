package com.example.andronews.domain.repo

import com.example.andronews.data.api.news.dto.Category
import com.example.andronews.data.api.news.dto.FollowRequest

interface NewsRepository {
    suspend fun getCategory(): List<Category>

    suspend fun followToggle(categoryId: Int)

}