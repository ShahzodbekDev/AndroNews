package com.example.andronews.domain.repo

import androidx.paging.PagingData
import com.example.andronews.data.api.news.dto.Banner
import com.example.andronews.data.api.news.dto.Category
import com.example.andronews.data.api.news.dto.News
import com.example.andronews.domain.model.NewsQuery
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getBanners(): List<Banner>

    suspend fun getCategory(): List<Category>

    suspend fun followToggle(categoryId: String)

    fun getNews(newsQuery: NewsQuery): Flow<PagingData<News>>

}