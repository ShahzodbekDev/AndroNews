package com.example.andronews.presntation.forgor_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.andronews.R
import com.example.andronews.databinding.FragmentForgotPasswordBinding
import com.example.andronews.presentation.sign_in.SignInViewModel
import com.example.andronews.util.BaseFragment
import com.example.andronews.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>(FragmentForgotPasswordBinding::inflate) {

    private val viewModel by viewModels<ForgotPasswordViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initUi()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() = with(binding) {
        viewModel.loading.observe(viewLifecycleOwner){ isLoading ->
            progressBar.isVisible = isLoading
            send.text = if (isLoading) null else getString(R.string.forgot_password_send)
        }

        viewModel.events.observe(viewLifecycleOwner) { event ->
            when (event) {
                ForgotPasswordViewModel.Event.ConnectionError -> toast(R.string.connection_error)
                ForgotPasswordViewModel.Event.Error -> toast(R.string.error)
                ForgotPasswordViewModel.Event.InvalidCredentials -> toast(R.string.invalid_credentials)
                ForgotPasswordViewModel.Event.NavigateToHome -> toast(R.string.app_name)
            }
        }
    }

    private fun initUi() = with(binding) {
        send.setOnClickListener {
            viewModel.forgotPassword(email.text.toString())
        }
    }

}