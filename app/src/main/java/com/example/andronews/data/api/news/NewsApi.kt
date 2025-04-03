package com.example.andronews.data.api.news

import com.example.andronews.data.api.news.dto.Category
import com.example.andronews.data.api.news.dto.FollowRequest
import com.example.andronews.data.api.news.dto.FollowResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NewsApi {

    @GET("news/categories")
    suspend fun getCategory(): List<Category>

    @POST("news/follow-toggle")
    suspend fun followToggle(@Body request: FollowRequest): FollowResponse

}