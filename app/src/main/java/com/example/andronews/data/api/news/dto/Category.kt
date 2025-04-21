package com.example.andronews.data.api.news.dto

import com.google.gson.annotations.SerializedName


data class Category(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("isFollowing")
    val isFollowing: Boolean
)

