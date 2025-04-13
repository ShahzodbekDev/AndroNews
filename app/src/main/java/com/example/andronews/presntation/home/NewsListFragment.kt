package com.example.andronews.presntation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.andronews.R

class NewsListFragment : Fragment() {

    companion object {
        private const val KEY_CATEGORY_ID = "category_id"

        fun newInstance(categoryId: Int): NewsListFragment {
            val fragment = NewsListFragment()
            val args = Bundle()
            args.putString(KEY_CATEGORY_ID, categoryId.toString())
            fragment.arguments = args
            return fragment
        }
    }

    private val categoryId: String? by lazy {
        arguments?.getString(KEY_CATEGORY_ID)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Log.d("NewsListFragment", "Category ID: $categoryId")
    }
}

