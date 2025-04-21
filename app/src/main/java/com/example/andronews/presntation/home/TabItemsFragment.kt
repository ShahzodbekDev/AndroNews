package com.example.andronews.presntation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.andronews.data.api.news.dto.Banner
import com.example.andronews.data.api.news.dto.News
import com.example.andronews.databinding.FragmentHomeTabItemsBinding
import com.example.andronews.presntation.home.adapters.BannerAdapter
import com.example.andronews.presntation.home.adapters.NewsAdapter
import com.example.andronews.presntation.home.viewModels.TabItemsViewModel
import com.example.andronews.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TabItemsFragment :
    BaseFragment<FragmentHomeTabItemsBinding>(FragmentHomeTabItemsBinding::inflate) {

    private val viewModel by viewModels<TabItemsViewModel>()

    private val newsAdapter by lazy {
        NewsAdapter(::onClickNews)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setCategory(categoryId)

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
            banner.adapter = BannerAdapter(it, ::onClickBanner)
        }

        viewModel.news.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
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

    private val categoryId: String? by lazy {
        arguments?.getString(KEY_CATEGORY_ID)
    }

    companion object {
        private const val KEY_CATEGORY_ID = "category_id"

        fun newInstance(categoryId: String): TabItemsFragment {
            val fragment = TabItemsFragment()
            val args = Bundle()
            args.putString(KEY_CATEGORY_ID, categoryId.toString())
            fragment.arguments = args
            return fragment
        }
    }

    private fun onClickBanner(banner: Banner) {

    }

    private fun onClickNews(news: News) {
        findNavController().navigate(TabItemsFragmentDirections.actionTabItemsFragmentToDetailsFragment())
    }

}

