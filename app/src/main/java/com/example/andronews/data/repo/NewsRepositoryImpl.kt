package com.example.andronews.data.repo

import android.util.Log
import com.example.andronews.data.api.news.NewsApi
import com.example.andronews.data.api.news.dto.FollowRequest
import com.example.andronews.domain.repo.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {
    override suspend fun getCategory() = newsApi.getCategory()

    override suspend fun followToggle(categoryId: Int) {
        val request = FollowRequest(categoryId)
        val response = newsApi.followToggle(request)
        Log.d("tag", "$response")
    }


}