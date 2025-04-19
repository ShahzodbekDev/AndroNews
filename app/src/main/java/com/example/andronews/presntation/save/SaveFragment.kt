package com.example.andronews.presntation.save

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.example.andronews.R
import com.example.andronews.databinding.FragmentSaveBinding
import com.example.andronews.util.BaseFragment
import com.google.android.material.tabs.TabLayout


class SaveFragment : BaseFragment<FragmentSaveBinding>(FragmentSaveBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() = with(binding) {

        deleteHistory.apply {
            outlineProvider = null
            clipToOutline = false
        }

        val tabAdapter = TabPagerAdapter(requireParentFragment())
        pager2.adapter = tabAdapter


        val tabTitles = listOf("Watch List", "History")

        for (i in tabTitles.indices) {
            val tab = tabLayout.newTab().setText(tabTitles[i])
            tabLayout.addTab(tab)
        }

        pager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    pager2.currentItem = it.position
                    deleteHistory.isVisible = (it.position == 1)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

    }

}