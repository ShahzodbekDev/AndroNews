package com.example.andronews.presntation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.andronews.R
import com.example.andronews.data.api.news.dto.Banner
import com.example.andronews.data.api.news.dto.News
import com.example.andronews.databinding.ItemBannerBinding
import com.example.andronews.util.getTimeAgo

class BannerAdapter(
    private val banner: List<Banner>,
    private val onClick: (banner: Banner) -> Unit
): RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemBannerBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(banner: Banner) = with(binding){
            Glide.with(root.context).load(banner.image).into(image)
            categoryTitle.text = root.context.getString(R.string.banner_news_category_title, banner.news.category)
            newsTitle.text = root.context.getString(R.string.banner_news_title, banner.news.title)

            val addTime = getTimeAgo(banner.news.addTime)
            newsAddTime.text = root.context.getString(R.string.banner_add_news_time, addTime)

            commentCount.text = root.context.getString(R.string.banner_news_comment_count, banner.news.commentCount)
            viewCount.text = root.context.getString(R.string.banner_news_views_count, banner.news.viewsCount)
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(
        ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent,false)
    )

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(banner[position])
    }

    override fun getItemCount() = banner.size

}