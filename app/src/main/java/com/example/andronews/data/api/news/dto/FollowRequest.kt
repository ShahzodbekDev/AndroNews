package com.example.andronews.data.api.news.dto

import com.google.gson.annotations.SerializedName

data class FollowRequest(
    @SerializedName("category_id")
    val categoryId: String
)