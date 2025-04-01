package com.example.andronews.data.store

import com.example.andronews.data.api.auth.dto.UserDto
import javax.inject.Inject

class UserStore @Inject constructor(): BaseStore<UserDto>("user", UserDto::class.java)
