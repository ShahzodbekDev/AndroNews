package com.example.andronews.presntation.search.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.andronews.data.api.news.dto.News
import com.example.andronews.databinding.ItemNewsBinding
import com.example.andronews.databinding.ItemSearchResultBinding

class SearchResultAdapter(
    private val onClick: (news: News) -> Unit
) : PagingDataAdapter<News, SearchResultViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = SearchResultViewHolder(
        ItemSearchResultBinding.inflate(
            LayoutInflater
                .from(parent.context),
            parent, false
        )
    )

    override fun onBindViewHolder(
        holder: SearchResultViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position) ?: return, onClick)
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(
                oldItem: News,
                newItem: News
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: News,
                newItem: News
            ) = oldItem == newItem

        }
    }


}