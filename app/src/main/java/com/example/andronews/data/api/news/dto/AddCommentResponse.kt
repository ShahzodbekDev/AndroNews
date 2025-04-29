package com.example.andronews.data.api.news.dto

import com.google.gson.annotations.SerializedName

data class AddCommentResponse(
    @SerializedName("status")
    val status: Boolean
)

