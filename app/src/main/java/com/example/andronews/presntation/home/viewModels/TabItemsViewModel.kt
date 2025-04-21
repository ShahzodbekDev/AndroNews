package com.example.andronews.presntation.home.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.cachedIn
import com.example.andronews.data.api.news.dto.Banner
import com.example.andronews.domain.model.NewsQuery
import com.example.andronews.domain.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TabItemsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {

    val loading = MutableLiveData(false)

    val error = MutableLiveData(false)


    private val category = MutableStateFlow<String?>(null)


    val news = category
        .map { it?: "" }
        .flatMapLatest { categoryId ->
            newsRepository.getNews(NewsQuery(categoryId = categoryId))
        }
        .cachedIn(viewModelScope)


    val banners = MutableLiveData<List<Banner>>()

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

    fun setCategory(categoryId: String?) {
        category.value = categoryId
        Log.d("tag","categoryId: $categoryId")
    }

    fun setLoadState(states : CombinedLoadStates){
        val loading = states.source.refresh is LoadState.Loading
        this.loading.postValue(loading)
    }
}


