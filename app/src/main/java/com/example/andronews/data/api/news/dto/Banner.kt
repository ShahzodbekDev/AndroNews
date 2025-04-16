package com.example.andronews.data.api.news.dto


import com.google.gson.annotations.SerializedName

data class Banner(
    @SerializedName("image")
    val image: String,
    @SerializedName("news")
    val news: News
)