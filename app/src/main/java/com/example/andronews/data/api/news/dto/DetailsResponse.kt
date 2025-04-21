package com.example.andronews.data.api.news.dto


import com.google.gson.annotations.SerializedName

data class DetailsResponse(
    @SerializedName("add_time")
    val addTime: String,
    @SerializedName("comments")
    val comments: List<Comment>,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("other_news")
    val otherNews: List<News>,
    @SerializedName("title")
    val title: String,
    @SerializedName("views_count")
    val viewsCount: Int
)