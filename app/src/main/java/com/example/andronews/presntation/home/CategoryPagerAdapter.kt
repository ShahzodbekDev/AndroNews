package com.example.andronews.presntation.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.andronews.data.api.news.dto.Category


class CategoryPagerAdapter(
    activity: FragmentActivity,
    private val categories: List<Category>
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = categories.size + 1 // 1ta Hot + Nta kategoriya

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            HotNewsFragment() // Hot fragment
        } else {
            val category = categories[position - 1]
            NewsListFragment.newInstance(category.id)
        }
    }
}

