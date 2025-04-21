package com.example.andronews.data.api.news

import com.example.andronews.data.api.news.dto.Banner
import com.example.andronews.data.api.news.dto.Category
import com.example.andronews.data.api.news.dto.DetailsResponse
import com.example.andronews.data.api.news.dto.FollowRequest
import com.example.andronews.data.api.news.dto.FollowResponse
import com.example.andronews.data.api.news.dto.News
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {


    @GET("banners")
    suspend fun getBanners() : List<Banner>

    @GET("news")
    suspend fun getNews(
        @Query("category_id") categoryId : String?,
        @Query("page") page : Int,
        @Query("size") size : Int
    ): List<News>

    @GET("news/{id}")
    suspend fun getNewsDetails(@Path("id") id :Int): DetailsResponse

    @GET("news/categories")
    suspend fun getCategory(): List<Category>

    @POST("news/follow-toggle")
    suspend fun followToggle(@Body request: FollowRequest): FollowResponse

}


