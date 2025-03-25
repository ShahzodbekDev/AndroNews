package com.example.andronews.data.api.auth.dto

import com.example.andronews.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("username")
    val username : String
){
    fun toUser() = User(
        username = username
    )

}
