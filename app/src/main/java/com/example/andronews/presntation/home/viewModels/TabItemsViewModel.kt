package com.example.andronews.presntation.home.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.andronews.data.api.news.dto.Category
import com.example.andronews.data.api.news.dto.News
import com.example.andronews.domain.model.NewsQuery
import com.example.andronews.domain.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TabItemsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {

    val loading = MutableLiveData(false)

    val news = MutableLiveData<PagingData<News>>()
    val category = MutableLiveData<Category>()

    fun setCategory(category: Category?) {
        this.category.postValue(category)
        getNewsByCategory()
    }

    fun getNewsByCategory() = viewModelScope.launch{
        val query = NewsQuery(category = category.value)
        newsRepository.getNews(query).collectLatest {
            news.postValue(it)
        }
    }


    fun setLoadState(states : CombinedLoadStates){
        val loading = states.source.refresh is LoadState.Loading
        this.loading.postValue(loading)
    }
}