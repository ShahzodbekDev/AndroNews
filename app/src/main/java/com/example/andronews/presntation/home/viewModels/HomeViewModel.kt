package com.example.andronews.presntation.home.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andronews.data.api.news.dto.Category
import com.example.andronews.data.api.news.dto.HomeResponse
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
    val error = MutableLiveData(false)
    val events = SingleLiveEvent<Event>()

    val categories = MutableLiveData<List<Category>?>(null)
    init {
        getCategories()
    }


    fun getCategories() = viewModelScope.launch {
        loading.postValue(true)
        error.postValue(false)
        try {
            val response = newsRepository.getCategory()
            categories.postValue(response)
        } catch (e: Exception) {
            error.postValue(true)
        } finally {
            loading.postValue(false)
        }
    }


    sealed class Event {
        data object ConnectionError : Event()
        data object Error : Event()
    }

}