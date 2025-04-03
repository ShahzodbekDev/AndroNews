package com.example.andronews.presntation.interestes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andronews.R
import com.example.andronews.databinding.FragmentInterestesBinding
import com.example.andronews.presentation.sign_in.SignInViewModel
import com.example.andronews.util.BaseFragment
import com.example.andronews.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InterestesFragment : BaseFragment<FragmentInterestesBinding>(FragmentInterestesBinding::inflate) {


    private val viewModel by viewModels<InterestesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() = with(binding){
        viewModel.loading.observe(viewLifecycleOwner){ loading ->
            progressBar.isVisible = loading
        }
        viewModel.categories.observe(viewLifecycleOwner) { categories ->
            interestesList.adapter = InterestesAdapter(categories) { categoryId ->
                viewModel.followStatus(categoryId)
            }

        }
        viewModel.events.observe(viewLifecycleOwner) { event ->

            when (event) {
                InterestesViewModel.Event.ConnectionError -> toast(R.string.connection_error)
                InterestesViewModel.Event.Error -> toast(R.string.error)
                InterestesViewModel.Event.InvalidCredentials -> toast(R.string.invalid_credentials)
                else -> toast(R.string.unknown_error)
            }
        }



    }

    private fun initUi() = with(binding){
        continueBtn.setOnClickListener {
            findNavController().navigate(InterestesFragmentDirections.toSignInFragment())
        }
    }

}


