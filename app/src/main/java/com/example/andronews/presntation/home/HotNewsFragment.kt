package com.example.andronews.presntation.home

import android.os.Bundle
import android.view.View
import com.example.andronews.databinding.FragmentHotnewsBinding
import com.example.andronews.util.BaseFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.example.andronews.data.api.news.dto.Banner
import com.example.andronews.data.api.news.dto.News
import com.example.andronews.presntation.home.adapters.BannerAdapter
import com.example.andronews.presntation.home.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HotNewsFragment : BaseFragment<FragmentHotnewsBinding>(FragmentHotnewsBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        subscribeToLiveData()

    }

    private fun subscribeToLiveData() = with(binding) {

        viewModel.home.observe(viewLifecycleOwner) {
            it?.banner?.let { bannerList ->
                banner.adapter = BannerAdapter(bannerList,::onClickBanner)
            }
        }

    }


    private fun initUi() = with(binding) {
//        scrollView.viewTreeObserver.addOnScrollChangedListener {
//            val scrollY = scrollView.scrollY
//
//            newsList.isNestedScrollingEnabled = scrollY > 470
//        }

    }

    private fun onClickBanner(banner: Banner){
        
    }



}