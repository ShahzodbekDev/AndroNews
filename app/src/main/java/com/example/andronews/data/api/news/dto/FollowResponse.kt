package com.example.andronews.data.api.news.dto

import com.google.gson.annotations.SerializedName

data class FollowResponse(
    @SerializedName("status")
    val status: Boolean
)
