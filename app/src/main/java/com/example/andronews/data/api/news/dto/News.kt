package com.example.andronews.data.api.news.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    @SerializedName("add_time")
    val addTime: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("comment_count")
    val commentCount: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("views_count")
    val viewsCount: Int,
):Parcelable