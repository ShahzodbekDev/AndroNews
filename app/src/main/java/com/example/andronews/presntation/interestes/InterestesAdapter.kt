package com.example.andronews.presntation.interestes

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.andronews.R
import com.example.andronews.data.api.news.dto.Category
import com.example.andronews.databinding.ItemInterestesBinding

class InterestesAdapter(
    private val interestes: List<Category>,
    private val onCategoryClick: (Int) -> Unit
) : RecyclerView.Adapter<InterestesAdapter.ViewHolder>() {


    class ViewHolder(private val binding: ItemInterestesBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(category: Category, onCategoryClick: (Int) -> Unit) = with(binding) {
            Glide.with(root.context).load(category.image).into(itemImage)
            itemName.text = category.name


            if (category.isFollowing) {
                followingBtn.apply {
                    text = "+ Follow"
                    setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            root.context,
                            R.drawable.iinterests_follow_button_background // Follow holati uchun background
                        )
                    )
                }

            } else{
                followingBtn.apply {
                    text = "Following"
                    setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            root.context,
                            R.drawable.interests_following_button_background // Following holati uchun background
                        )
                    )
                }
            }
            followingBtn.setOnClickListener {
                onCategoryClick(category.id)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemInterestesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = interestes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(interestes[position], onCategoryClick)
    }


}