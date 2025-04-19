package com.example.andronews.presntation.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.andronews.data.api.news.dto.Banner
import com.example.andronews.data.api.news.dto.News
import com.example.andronews.databinding.FragmentHotnewsBinding
import com.example.andronews.presntation.home.adapters.BannerAdapter
import com.example.andronews.presntation.home.adapters.NewsAdapter
import com.example.andronews.presntation.home.viewModels.TabItemsViewModel
import com.example.andronews.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HotNewsFragment : BaseFragment<FragmentHotnewsBinding>(FragmentHotnewsBinding::inflate) {

    private val viewModel by viewModels<TabItemsViewModel>()

    private val newsAdapter by lazy {
        NewsAdapter(::onClickNews)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setCategory("")

        newsAdapter.addLoadStateListener {
            viewModel.setLoadState(it)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        subscribeToLiveData()

    }

    private fun subscribeToLiveData() = with(binding) {

        viewModel.banners.observe(viewLifecycleOwner) {
            banner.adapter = BannerAdapter(it,::onClickBanner)
            Log.d("tag","news: $it")
        }

        viewModel.news.observe(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch {
                newsAdapter.submitData(it)
            }
        }
    }


    private fun initUi() = with(binding) {

        scrollView.viewTreeObserver.addOnScrollChangedListener {
            val scrollY = scrollView.scrollY

            news.isNestedScrollingEnabled = scrollY > 470
        }
        news.adapter = newsAdapter

    }

    private fun onClickBanner(banner: Banner) {

    }

    private fun onClickNews(news: News) {

    }


}