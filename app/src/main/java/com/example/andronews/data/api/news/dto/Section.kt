package com.example.andronews.data.api.news.dto


import com.google.gson.annotations.SerializedName

data class Section(
    @SerializedName("comments")
    val comments: List<Comment>,
    @SerializedName("id")
    val id: String,
    @SerializedName("other_new")
    val otherNew: List<News>,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: SectionType
)