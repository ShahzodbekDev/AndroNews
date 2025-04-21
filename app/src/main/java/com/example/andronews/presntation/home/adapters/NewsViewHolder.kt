package com.example.andronews.presntation.home.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.andronews.R
import com.example.andronews.data.api.news.dto.News
import com.example.andronews.databinding.ItemNewsBinding
import com.example.andronews.util.getTimeAgo

class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(news: News, onClick: (News) -> Unit) = with(binding) {

        Glide.with(root.context).load(news.image).into(image)
        categoryTitle.text = root.context.getString(R.string.news_category_title, news.category)
        newsTitle.text = root.context.getString(R.string.news_title, news.title)

        val formattedAddTime = getTimeAgo(news.addTime)
        newsAddTime.text = root.context.getString(R.string.news_add_time, formattedAddTime)

        commentCount.text = root.context.getString(R.string.news_comment_count, news.commentCount)

        viewUsers.text = root.context.getString(R.string.news_view_users, news.viewsCount)

        root.setOnClickListener {
          onClick(news)
        }
    }

}