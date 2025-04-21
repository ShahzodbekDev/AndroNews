package com.example.andronews.data.repo

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.andronews.data.api.news.NewsApi
import com.example.andronews.data.api.news.paging.NewsPagingSource
import com.example.andronews.data.api.news.dto.FollowRequest
import com.example.andronews.data.api.news.dto.News
import com.example.andronews.domain.model.NewsQuery
import com.example.andronews.domain.repo.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
) : NewsRepository {
    override suspend fun getBanners() = newsApi.getBanners()

    override suspend fun getCategory() = newsApi.getCategory()

    override suspend fun followToggle(categoryId: String) {
        val request = FollowRequest(categoryId)
        val response = newsApi.followToggle(request)
        Log.d("tag", "id:$categoryId $response")
    }



    override fun getNews(newsQuery: NewsQuery) = Pager(
        config = PagingConfig(
            pageSize = 10,
            prefetchDistance = 10,
            enablePlaceholders = false,
            initialLoadSize = 20
        ),
        initialKey = 0,
        pagingSourceFactory = {
            NewsPagingSource(newsApi, newsQuery)
        }
    ).flow




}