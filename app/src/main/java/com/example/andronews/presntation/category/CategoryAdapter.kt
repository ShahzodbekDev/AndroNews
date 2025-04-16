package com.example.andronews.presntation.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.andronews.R
import com.example.andronews.data.api.news.dto.Category
import com.example.andronews.databinding.ItemCategoryBinding

class CategoryAdapter(
    private val categories: List<Category>,
    private val onCategoryClick: (String) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category, onCategoryClick: (String) -> Unit) = with(binding) {
            Glide.with(root.context).load(category.image).into(image)
            name.text = category.name

            followingBtn.setOnClickListener {
                onCategoryClick(category.id)
            }

            setupFollowButton(category)
        }




        private fun setupFollowButton(category: Category) = with(binding.followingBtn) {
            val context = binding.root.context
            val (textRes, bgRes, textColor) = if (category.isFollowing) {
                Triple(R.string.item_interests_title_follow, R.drawable.interests_follow_button_background, R.color.followText)
            } else {
                Triple(R.string.item_interests_title_following, R.drawable.interests_following_button_background, R.color.white)
            }

            text = context.getString(textRes)
            background = ContextCompat.getDrawable(context, bgRes)
            setTextColor(ContextCompat.getColor(context, textColor))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position], onCategoryClick)
    }



}