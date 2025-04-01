package com.example.andronews.data.store

import javax.inject.Inject

class SplashedStore @Inject constructor(): BaseStore<Boolean>("splashed", Boolean::class.java) {
}