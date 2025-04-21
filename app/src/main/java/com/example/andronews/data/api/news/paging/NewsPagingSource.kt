package com.example.andronews.data.api.news.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.andronews.data.api.news.NewsApi
import com.example.andronews.data.api.news.dto.News
import com.example.andronews.domain.model.NewsQuery

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val newsQuery: NewsQuery
) : PagingSource<Int, News>() {
    override fun getRefreshKey(state: PagingState<Int, News>) = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
        return try {
            val key = params.key ?: 0

            val news = newsApi.getNews(
                categoryId = newsQuery.categoryId,
                search = newsQuery.search,
                page = key,
                size = params.loadSize
            )

            LoadResult.Page(
                data = news,
                prevKey = params.key?.let { it - 1 }?.takeIf { it > 0 },
                nextKey = if (news.isNotEmpty()) key + 1 else null
            )

        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}