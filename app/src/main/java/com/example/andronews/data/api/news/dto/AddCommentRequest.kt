package com.example.andronews.data.api.news.dto

import com.google.gson.annotations.SerializedName

data class AddCommentRequest(
    @SerializedName("comment_text")
    val commentText: String
)