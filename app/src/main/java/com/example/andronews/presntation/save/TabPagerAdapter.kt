package com.example.andronews.presntation.save

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabItemSaveFragment()
            1 -> TabItemHistoryFragment()
            else -> throw IllegalArgumentException("Invalid tab position")
        }
    }

    override fun getItemCount() = 2
}