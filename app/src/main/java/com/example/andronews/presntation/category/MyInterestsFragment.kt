package com.example.andronews.presntation.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.andronews.databinding.FragmentMyinterestsBinding
import com.example.andronews.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyInterestsFragment : BaseFragment<FragmentMyinterestsBinding>(FragmentMyinterestsBinding::inflate) {

    private val viewModel by viewModels<CategoryViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() = with(binding){
        viewModel.loading.observe(viewLifecycleOwner){
            loading.root.isVisible = it
        }
        viewModel.error.observe(viewLifecycleOwner) {
            error.root.isVisible = it
        }
        viewModel.categories.observe(viewLifecycleOwner) {
            val isNotEmpty = !it.isNullOrEmpty()
            myInterests.isVisible = isNotEmpty

            if (!isNotEmpty) return@observe

            categoryList.adapter = CategoryAdapter(it) { categoryId ->
                viewModel.followStatus(categoryId)
            }
        }

    }

    private fun initUi() = with(binding){
        error.retry.setOnClickListener {
            viewModel.getCategories()
        }
    }
}