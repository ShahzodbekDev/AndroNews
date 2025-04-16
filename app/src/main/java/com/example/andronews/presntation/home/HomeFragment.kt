package com.example.andronews.presntation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.andronews.data.api.news.dto.News
import com.example.andronews.databinding.FragmentHomeBinding
import com.example.andronews.presntation.home.adapters.CategoryPagerAdapter
import com.example.andronews.presntation.home.viewModels.HomeViewModel
import com.example.andronews.util.BaseFragment
import com.example.andronews.util.setLightStatusBar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() = with(binding){
        viewModel.loading.observe(viewLifecycleOwner) {
            loading.root.isVisible = it
        }
        viewModel.error.observe(viewLifecycleOwner) {
            error.root.isVisible = it
        }
        viewModel.home.observe(viewLifecycleOwner) {
            home.isVisible = it != null
            it ?: return@observe

            val adapter = CategoryPagerAdapter(requireActivity(), it.categories)
            pager2.adapter = adapter

            TabLayoutMediator(tabLayout, pager2) { tab, position ->
                tab.text = if (position == 0) "Hot" else it.categories[position - 1].name
            }.attach()
        }






    }


    private fun initUi() = with(binding){
        setLightStatusBar()

    }



}