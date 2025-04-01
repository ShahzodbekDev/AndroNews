package com.example.andronews.presntation.sign_up

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andronews.domain.repo.AuthRepository
import com.example.andronews.presentation.sign_in.SignInViewModel.Event
import com.example.andronews.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val auhtRepository: AuthRepository
) : ViewModel() {

    val loading = MutableLiveData(false)
    val events = SingleLiveEvent<Event>()

    fun signUp(username: String, email: String, password: String) =
        viewModelScope.launch(Dispatchers.IO) {
            loading.postValue(true)
            try {
                auhtRepository.signUp(username, email, password)
                events.postValue(Event.NavigateToHome)
            } catch (e: Exception) {
                when {
                    e is HttpException && e.code() == 403 -> events.postValue(Event.AlreadyRegistered)

                    e is IOException -> events.postValue(Event.ConnectionError)
                    else -> events.postValue(Event.Error)
                }
            } finally {
                loading.postValue(false)
            }
        }

    sealed class Event {
        data object AlreadyRegistered : Event()
        data object ConnectionError : Event()
        data object Error : Event()
        data object NavigateToHome : Event()
    }

}