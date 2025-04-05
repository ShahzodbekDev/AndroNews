package com.example.andronews.presntation.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.andronews.R
import com.example.andronews.databinding.FragmentSplashBinding
import com.example.andronews.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private val adapter = SplashAdapter()
    private val viewModel by viewModels<SplashViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       initUi()

    }

    private fun initUi() = with(binding){

        pager.adapter = adapter

        continueBtn.setOnClickListener {
            if (pager.currentItem == adapter.itemCount -1){
                viewModel.splashed()
                findNavController().navigate(SplashFragmentDirections.toInterestsFragment())
            }else {
                pager.setCurrentItem(pager.currentItem + 1, true)
            }
        }

    }


}