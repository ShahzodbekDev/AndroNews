package com.example.andronews.presntation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.andronews.R
import com.example.andronews.data.api.news.dto.News
import com.example.andronews.databinding.ItemNewsBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

class NewsAdapter(
    private val newsList: List<News>,
    private val onClick: (news: News) -> Unit
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    class ViewHolder(private val binding: ItemNewsBinding)
        : RecyclerView.ViewHolder(binding.root)  {
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

        private fun getTimeAgo(dateStr: String): String {
            val format = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
            format.timeZone = TimeZone.getDefault()

            val addDate = format.parse(dateStr) ?: return ""
            val now = Date()

            val diffInMillis = now.time - addDate.time
            val minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis)
            val hours = TimeUnit.MILLISECONDS.toHours(diffInMillis)
            val days = TimeUnit.MILLISECONDS.toDays(diffInMillis)

            return when {
                minutes < 1 -> "just now"
                minutes < 60 -> "$minutes min"
                hours < 24 -> "$hours hour"
                days < 7 -> "$days day"
                days < 30 -> "${days / 7} week"
                days < 365 -> "${days / 30} month"
                else -> "${days / 365} year"
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
       holder.bind(newsList[position], onClick)
    }

    override fun getItemCount() = newsList.size



}