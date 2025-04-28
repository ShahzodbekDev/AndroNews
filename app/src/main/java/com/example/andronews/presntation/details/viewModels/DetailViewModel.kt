package com.example.andronews.presntation.details.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andronews.data.api.news.dto.Comment
import com.example.andronews.data.api.news.dto.Detail
import com.example.andronews.domain.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val loading = MutableLiveData(false)
    val error = MutableLiveData(false)


    val detail = MutableLiveData<Detail>()
    val comments = MutableLiveData<List<Comment>>()


    fun getDetail(id: String) = viewModelScope.launch {
        loading.postValue(true)
        try {
            val response = newsRepository.getDetails(id)
            detail.postValue(response)
        } catch (e: Exception) {
            error.postValue(true)
        } finally {
            loading.postValue(false)
        }
    }


    fun getComments(id: String) = viewModelScope.launch {
        loading.postValue(true)
        try {
            val response = newsRepository.getComments(id)
            comments.postValue(response)
        } catch (e: Exception) {
            error.postValue(true)
        } finally {
            loading.postValue(false)
        }

    }

}