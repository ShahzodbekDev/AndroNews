package com.example.andronews.presentation.sign_in

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andronews.domain.repo.AuthRepository
import com.example.andronews.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

     val loading = MutableLiveData(false)
     val events = SingleLiveEvent<Event>()

    fun signIn(username: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        loading.postValue(true)
        try {
            authRepository.signIn(username, password)

        } catch (e: Exception) {
            when {
                e is HttpException && e.code() == 404 -> events.postValue(Event.InvalidCredentials)
                e is IOException -> events.postValue(Event.ConnectionError)
                else -> events.postValue(Event.Error)
            }

        } finally {
            loading.postValue(false)
        }
    }

    sealed class Event {
        data object InvalidCredentials : Event()
        data object ConnectionError : Event()
        data object Error : Event()
        data object NavigateToHome : Event()
    }
}