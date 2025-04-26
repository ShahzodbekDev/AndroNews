package com.example.andronews.data.api.news.dto


import com.google.gson.annotations.SerializedName

data class ThoughtUser(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("user_name")
    val userName: String
)