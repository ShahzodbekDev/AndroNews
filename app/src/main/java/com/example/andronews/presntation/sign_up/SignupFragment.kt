package com.example.andronews.presntation.sign_up

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.andronews.R
import com.example.andronews.databinding.FragmentSignUpBinding
import com.example.andronews.util.BaseFragment
import com.example.andronews.util.clearLightStatusBar
import com.example.andronews.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate) {

    private val viewModel by viewModels<SignupViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        subscribeToLiveData()

    }

    private fun subscribeToLiveData() = with(binding) {

        viewModel.loading.observe(viewLifecycleOwner) {
            progressBar.isVisible = it
            register.text = if (it) null else getString(R.string.sign_up_register_text)
        }

        viewModel.events.observe(viewLifecycleOwner) {

            when (it) {
                SignupViewModel.Event.ConnectionError -> toast(R.string.connection_error)
                SignupViewModel.Event.Error -> toast(R.string.error)
                SignupViewModel.Event.AlreadyRegistered -> toast(R.string.already_registered)
                SignupViewModel.Event.NavigateToHome -> toast(R.string.app_name)
                else -> toast(R.string.unknown_error)
            }
        }

    }

    private fun initUi() = with(binding) {
        clearLightStatusBar()
        register.setOnClickListener {
            viewModel.signUp(
                userName.text.toString(),
                email.text.toString(),
                password.text.toString()
            )
        }

    }

}