package com.example.andronews.presntation.sign_in

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.andronews.R
import com.example.andronews.databinding.FragmentSignInBinding
import com.example.andronews.presentation.sign_in.SignInViewModel
import com.example.andronews.util.BaseFragment
import com.example.andronews.util.clearLightStatusBar
import com.example.andronews.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {

    private val viewModel by viewModels<SignInViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        subscribeToLiveData()

    }

    private fun subscribeToLiveData() = with(binding) {

        viewModel.loading.observe(viewLifecycleOwner) {
            progressBar.isVisible = it
            login.text = if (it) null else getString(R.string.fragment_sign_in_login)
        }

        viewModel.events.observe(viewLifecycleOwner) {

            when (it) {
                SignInViewModel.Event.ConnectionError -> toast(R.string.connection_error)
                SignInViewModel.Event.Error -> toast(R.string.error)
                SignInViewModel.Event.InvalidCredentials -> toast(R.string.invalid_credentials)
                SignInViewModel.Event.NavigateToHome -> toast(R.string.app_name)
            }
        }
    }

    private fun initUi() = with(binding) {
        clearLightStatusBar()
        login.setOnClickListener {
            viewModel.signIn(userName.text.toString(), password.text.toString())
        }

        create.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.toSignupFragment())
        }

        forgotPassword.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.toForgotPasswordFragment())
        }
    }

}