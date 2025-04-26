package com.example.andronews.data.api.news.dto


import com.google.gson.annotations.SerializedName

data class Detail(
    @SerializedName("add_time")
    val addTime: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("comment_count")
    val commentCount: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("is_save")
    val isSave: Boolean,
    @SerializedName("section")
    val section: List<Section>,
    @SerializedName("title")
    val title: String,
    @SerializedName("views_count")
    val viewsCount: Int
)