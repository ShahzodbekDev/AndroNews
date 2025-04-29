package com.example.andronews.data.api.news.dto


import com.example.andronews.data.api.auth.dto.UserDto
import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("add_time")
    val addTime: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("comment_text")
    val commentText: String,
    @SerializedName("user")
    val user: UserDto
)