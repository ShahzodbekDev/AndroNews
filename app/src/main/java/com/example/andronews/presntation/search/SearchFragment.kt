package com.example.andronews.presntation.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.andronews.R
import com.example.andronews.data.api.news.dto.News
import com.example.andronews.databinding.FragmentSearchBinding
import com.example.andronews.presntation.search.adapters.SearchResultAdapter
import com.example.andronews.util.BaseFragment
import com.example.andronews.util.hideKeyboard
import com.example.andronews.util.showKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel by viewModels<SearchViewModel>()

    private var submitJob: Job? = null

    private val searchResultAdapter by lazy {
        SearchResultAdapter(::onClickNews)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        searchResultAdapter.addLoadStateListener {
            viewModel.setLoadState(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        subscribeToLiveData()

    }


    private fun subscribeToLiveData() = with(binding) {

        viewModel.loading.observe(viewLifecycleOwner) {
            progressBar.isVisible = it
        }

        viewModel.news.observe(viewLifecycleOwner) {
            if (it == null) {
                emptySearchResult.isVisible = true
            } else {
                emptySearchResult.isVisible = false
                viewLifecycleOwner.lifecycleScope.launch {
                    searchResultAdapter.submitData(it)
                }

            }
        }

    }

    private fun initUi() = with(binding) {
        search.requestFocus()
        showKeyboard()

        back.setOnClickListener {
            findNavController().popBackStack()
        }

        search.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.setSearch(search.text.toString())

                viewLifecycleOwner.lifecycleScope.launch {
                    searchResultAdapter.submitData(PagingData.empty())
                }

                hideKeyboard()
                search.clearFocus()
                return@OnEditorActionListener true
            }
            false
        })

        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_search)?.mutate()
        drawable?.setTint(ContextCompat.getColor(requireContext(), R.color.silver_chalice))
        search.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)

        search.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                drawable?.setTint(ContextCompat.getColor(requireContext(), R.color.ruby_red))
            } else {
                drawable?.setTint(ContextCompat.getColor(requireContext(), R.color.silver_chalice))
            }
            search.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
        }

        searchResult.adapter = searchResultAdapter

    }

    private fun onClickNews(news: News) {
        findNavController().navigate(SearchFragmentDirections.toDetailFragment(news.id))
    }
}