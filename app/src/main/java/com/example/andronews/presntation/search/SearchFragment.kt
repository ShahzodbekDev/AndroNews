package com.example.andronews.presntation.search

import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import com.example.andronews.R
import com.example.andronews.databinding.FragmentSearchBinding
import com.example.andronews.presntation.main.MainActivity
import com.example.andronews.util.BaseFragment


class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        subscribeToLiveData()

    }


    private fun subscribeToLiveData() = with(binding){

    }

    private fun initUi() = with(binding){
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_search)?.mutate()
        drawable?.setTint(ContextCompat.getColor(requireContext(), R.color.silver_chalice))
        search.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)




        back.setOnClickListener {
          findNavController().popBackStack()
        }
    }
}