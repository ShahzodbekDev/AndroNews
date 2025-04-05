package com.example.andronews.presntation.interestes

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.andronews.R
import com.example.andronews.databinding.FragmentInterestsBinding
import com.example.andronews.presntation.interestes.InterestsFragmentDirections
import com.example.andronews.util.BaseFragment
import com.example.andronews.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InterestsFragment : BaseFragment<FragmentInterestsBinding>(FragmentInterestsBinding::inflate) {


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
            interestesList.adapter = InterestsAdapter(categories) { categoryId ->
                viewModel.followStatus(categoryId)
            }
        }
        viewModel.events.observe(viewLifecycleOwner) { event ->

            when (event) {
                InterestesViewModel.Event.ConnectionError -> toast(R.string.connection_error)
                InterestesViewModel.Event.Error -> toast(R.string.error)
                InterestesViewModel.Event.InvalidCredentials -> toast(R.string.invalid_credentials)
            }
        }



    }

    private fun initUi() = with(binding){
        continueBtn.setOnClickListener {
            findNavController().navigate(InterestsFragmentDirections.toSignInFragment())
        }
    }

}


