package com.example.andronews.presntation.home.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.andronews.data.api.news.dto.Banner
import com.example.andronews.data.api.news.dto.News
import com.example.andronews.domain.model.NewsQuery
import com.example.andronews.domain.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TabItemsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val loading = MutableLiveData(false)
    val error = MutableLiveData(false)
    val category = MutableLiveData<String?>()

    val banners = MutableLiveData<List<Banner>>()

    val news = MutableLiveData<PagingData<News>>()


    init {
        getBanners()
    }

    private fun getBanners() = viewModelScope.launch {
        loading.postValue(true)
        error.postValue(false)
        try {
            val response = newsRepository.getBanners()
            banners.postValue(response)
        } catch (e: Exception) {
            error.postValue(true)
        } finally {
            loading.postValue(false)
        }
    }

    private var newsJob: Job? = null

    fun getNews() {
        newsJob?.cancel()
        newsJob = viewModelScope.launch {
            val query = NewsQuery(categoryId = category.value)
            newsRepository.getNews(query).collectLatest {
                news.postValue(it)
            }
        }
    }

    fun setCategory(categoryId: String?) {
        category.value = categoryId
        Log.d("tag", "categoryId: $categoryId")
        getNews()
    }

    fun setLoadState(states: CombinedLoadStates) {
        val loading = states.source.refresh is LoadState.Loading
        this.loading.postValue(loading)
    }
}


