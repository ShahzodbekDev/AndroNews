package com.example.andronews.data.api.news.dto

import androidx.paging.PagingData
import com.example.andronews.data.api.auth.dto.UserDto

data class HomeResponse(
    val banner: List<Banner>,
    val categories: List<Category>,
    val news: List<News>,
    val user: UserDto
)