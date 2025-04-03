package com.example.andronews.presntation.interestes

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andronews.data.api.news.dto.Category
import com.example.andronews.domain.repo.NewsRepository
import com.example.andronews.presentation.sign_in.SignInViewModel
import com.example.andronews.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class InterestesViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val error = MutableLiveData(false)
    val loading = MutableLiveData(false)

    val events = SingleLiveEvent<Event>()

    val categories = MutableLiveData<List<Category>>()

    init {
        getCategoryies()
    }

    fun getCategoryies() = viewModelScope.launch {
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

    fun followStatus(categoryId: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            newsRepository.followToggle(categoryId)

        } catch (e: Exception) {
            when {
                e is HttpException && e.code() == 404 -> events.postValue(InterestesViewModel.Event.InvalidCredentials)
                e is IOException -> events.postValue(InterestesViewModel.Event.ConnectionError)
                else -> events.postValue(InterestesViewModel.Event.Error)
            }
        }
    }

    sealed class Event {
        data object InvalidCredentials : Event()
        data object ConnectionError : Event()
        data object Error : Event()
    }
}