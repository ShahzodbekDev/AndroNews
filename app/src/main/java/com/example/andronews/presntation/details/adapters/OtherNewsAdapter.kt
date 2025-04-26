package com.example.andronews.presntation.details.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.andronews.R
import com.example.andronews.data.api.news.dto.News
import com.example.andronews.databinding.ItemOtherNewsBinding

class OtherNewsAdapter(
    private val news: List<News>,
    private val onClick: (news: News) -> Unit
) : RecyclerView.Adapter<OtherNewsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemOtherNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) = with(binding) {
            Glide.with(root).load(news.image).into(image)

            categoryName.text =
                root.context.getString(R.string.item_other_news_category_name, news.category)

            newsTitle.text = root.context.getString(R.string.item_other_news_title, news.title)

            root.setOnClickListener {
                onClick(news)
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(
        ItemOtherNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(news[position])
    }

    override fun getItemCount() = news.size


}