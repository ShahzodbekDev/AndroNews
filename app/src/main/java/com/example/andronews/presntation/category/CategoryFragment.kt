package com.example.andronews.presntation.category

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.andronews.R
import com.example.andronews.databinding.FragmentCategoryBinding
import com.example.andronews.util.BaseFragment
import com.example.andronews.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>(FragmentCategoryBinding::inflate) {


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
            categoryLayout.isVisible = isNotEmpty

            if (!isNotEmpty) return@observe

            interestesList.adapter = CategoryAdapter(it) { categoryId ->
                viewModel.followStatus(categoryId)
            }
        }

    }

    private fun initUi() = with(binding){

        error.retry.setOnClickListener {
            viewModel.getCategories()
        }

        continueBtn.setOnClickListener {
            findNavController().navigate(CategoryFragmentDirections.toSignInFragment())
        }
    }

}


