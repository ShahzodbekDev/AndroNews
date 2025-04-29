package com.example.andronews.presntation.details.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andronews.data.api.news.dto.Comment
import com.example.andronews.data.api.news.dto.Detail
import com.example.andronews.domain.repo.NewsRepository
import com.example.andronews.presentation.sign_in.SignInViewModel
import com.example.andronews.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val loading = MutableLiveData(false)
    val error = MutableLiveData(false)
    val sendProgress = MutableLiveData(false)
    val events = SingleLiveEvent<Event>()
    val detail = MutableLiveData<Detail>()
    val comments = MutableLiveData<List<Comment>>()

    val singleComment = MutableLiveData<Comment>()


    fun getDetail(id: String) = viewModelScope.launch {
        loading.postValue(true)
        try {
            val response = newsRepository.getDetails(id)
            detail.postValue(response)
        } catch (e: Exception) {
            Log.d("tag","detailError: $e")
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


    fun addComment(id: String, commentText: String) = viewModelScope.launch(Dispatchers.IO) {
        sendProgress.postValue(true)
        try {
            newsRepository.addComment(id, commentText)
        } catch (e: Exception) {
            when {
                e is HttpException && e.code() == 404 -> events.postValue(DetailViewModel.Event.InvalidCredentials)

                e is IOException -> events.postValue(DetailViewModel.Event.ConnectionError)
                else -> events.postValue(DetailViewModel.Event.Error)

            }
        } finally {
            sendProgress.postValue(false)
        }
    }


    fun getSingleComment(nid: String, cid: Int) = viewModelScope.launch {
        sendProgress.postValue(true)
        try {
            val response = newsRepository.getSingleComment(nid,cid)
        } catch (e: Exception) {
            when {
                e is HttpException && e.code() == 404 -> events.postValue(DetailViewModel.Event.InvalidCredentials)

                e is IOException -> events.postValue(DetailViewModel.Event.ConnectionError)
                else -> events.postValue(DetailViewModel.Event.Error)

            }
        } finally {
            sendProgress.postValue(false)
        }
    }

    sealed class Event {
        data object InvalidCredentials : Event()
        data object ConnectionError : Event()
        data object Error : Event()
    }

}