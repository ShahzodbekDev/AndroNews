package com.example.andronews.data.api.auth.dto

import com.example.andronews.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("user_name")
    val username : String,
    @SerializedName("avatar")
    val avatar : String
){
    fun toUser() = User(
        username = username,
        avatar = avatar
    )

}
