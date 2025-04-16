package com.example.andronews.presntation.home.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.andronews.data.api.news.dto.Category
import com.example.andronews.presntation.home.HotNewsFragment
import com.example.andronews.presntation.home.TabItemsFragment

class CategoryPagerAdapter(
    activity: FragmentActivity,
    private val categories: List<Category>
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = categories.size + 1

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            HotNewsFragment()
        } else {
            val category = categories[position - 1]
            TabItemsFragment.newInstance(category.id)
        }
    }
}