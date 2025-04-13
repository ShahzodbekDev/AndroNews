package com.example.andronews.presntation.home

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.andronews.databinding.FragmentHotnewsBinding
import com.example.andronews.util.BaseFragment
import androidx.fragment.app.viewModels
import com.example.andronews.data.api.news.dto.News
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotNewsFragment : BaseFragment<FragmentHotnewsBinding>(FragmentHotnewsBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        subscribeToLiveData()

    }

    private fun subscribeToLiveData() = with(binding) {

        viewModel.news.observe(viewLifecycleOwner) { news ->
            newsList.adapter = NewsAdapter(news, this@HotNewsFragment::onClick)
        }


    }

    private fun initUi() = with(binding) {
        scrollView.viewTreeObserver.addOnScrollChangedListener {
            val scrollY = scrollView.scrollY

            newsList.isNestedScrollingEnabled = scrollY > 470
        }

    }
    private fun onClick(news: News){

    }



}