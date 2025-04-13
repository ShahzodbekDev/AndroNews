package com.example.andronews.presntation.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andronews.data.api.news.dto.Category
import com.example.andronews.data.api.news.dto.News
import com.example.andronews.domain.repo.NewsRepository
import com.example.andronews.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val loading = MutableLiveData(false)
    val events = SingleLiveEvent<Event>()

    val categories = MutableLiveData<List<Category>>()
    val news = MutableLiveData<List<News>>()

    init {
        getCategories()
        getNews()
    }

    fun getCategories() = viewModelScope.launch {
        loading.postValue(true)
        try {
            val response = newsRepository.getCategory()
            categories.postValue(response)
        } catch (e: Exception) {

        } finally {
            loading.postValue(false)
        }
    }

    fun getNews() = viewModelScope.launch {
        try {
            val response = newsRepository.getNews()
            news.postValue(response)
            Log.d("tag","news: $response")

        } catch (e: Exception){


        } finally {

        }
    }


    sealed class Event {
        data object ConnectionError : Event()
        data object Error : Event()
    }

}