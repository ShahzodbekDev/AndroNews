package com.example.andronews.presntation.interestes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andronews.R
import com.example.andronews.databinding.FragmentInterestesBinding
import com.example.andronews.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InterestesFragment : BaseFragment<FragmentInterestesBinding>(FragmentInterestesBinding::inflate) {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() = with(binding){
        continueBtn.setOnClickListener {
            findNavController().navigate(InterestesFragmentDirections.toSignInFragment())
        }

        interestesList.apply {
            adapter = InterestesAdapter()
            layoutManager = LinearLayoutManager(context)
        }
    }

}