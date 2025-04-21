package com.example.andronews.presntation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andronews.R
import com.example.andronews.databinding.FragmentDetailsBinding
import com.example.andronews.util.BaseFragment

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initUi()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() = with(binding) {

    }

    private fun initUi() = with(binding) {

    }

}