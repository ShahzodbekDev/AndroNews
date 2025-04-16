package com.example.andronews.presntation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.andronews.databinding.FragmentTabItemsBinding
import com.example.andronews.presntation.home.viewModels.TabItemsViewModel
import com.example.andronews.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TabItemsFragment : BaseFragment<FragmentTabItemsBinding>(FragmentTabItemsBinding::inflate) {


    private val viewModel by viewModels<TabItemsViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        subscribeToLiveData()

    }

    private fun subscribeToLiveData() = with(binding) {



    }

    private fun initUi() = with(binding) {

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

}

