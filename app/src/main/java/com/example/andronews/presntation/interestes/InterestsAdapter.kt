package com.example.andronews.presntation.interestes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.andronews.R
import com.example.andronews.data.api.news.dto.Category
import com.example.andronews.databinding.ItemInterestesBinding

class InterestsAdapter(
    private val interests: List<Category>,
    private val onCategoryClick: (Int) -> Unit
) : RecyclerView.Adapter<InterestsAdapter.ViewHolder>() {


    class ViewHolder(private val binding: ItemInterestesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category, onCategoryClick: (Int) -> Unit) = with(binding) {
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
                Triple(R.string.item_interests_title_follow, R.drawable.iinterests_follow_button_background, R.color.followText)
            } else {
                Triple(R.string.item_interests_title_following, R.drawable.interests_following_button_background, R.color.white)
            }

            text = context.getString(textRes)
            background = ContextCompat.getDrawable(context, bgRes)
            setTextColor(ContextCompat.getColor(context, textColor))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemInterestesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = interests.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(interests[position], onCategoryClick)
    }



}