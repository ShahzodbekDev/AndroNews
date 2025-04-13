package com.example.andronews.domain.model

sealed class Destination {
    data object Home : Destination()
    data object Interests : Destination()
    data object Splash : Destination()
    data object SignIn : Destination()
}