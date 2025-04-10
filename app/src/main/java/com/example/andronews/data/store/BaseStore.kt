package com.example.andronews.data.store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

abstract class BaseStore<T>(
    private val key: String,
    private val classOFT : Class<T>
) {

    @Inject
    lateinit var dataStore: DataStore<Preferences>

    @Inject
    lateinit var gson: Gson

    suspend fun set(value: T){
        dataStore.edit {
            it[stringPreferencesKey(key)] = gson.toJson(value)
        }
    }

    suspend fun get() = getFlow().firstOrNull()

     fun getFlow() = dataStore.data.map {
        try {
            val json = it[stringPreferencesKey(key)]
            gson.fromJson(json, classOFT)
        } catch (e: Exception){
            null
        }
    }

    suspend fun clear(){
        dataStore.edit {
            it.remove(stringPreferencesKey(key))
        }
    }

}