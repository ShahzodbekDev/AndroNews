package com.example.andronews.presntation.interestes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.andronews.R
import com.example.andronews.databinding.ItemInterestesBinding

class InterestesAdapter : RecyclerView.Adapter<InterestesAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemInterestesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Triple<Int,String,String>) = with(binding) {
            Glide.with(root.context).load(item.first).into(itemImage)
            itemName.text = item.second

            if (item.third == "+ follow") {
                followingBtn.apply {
                    setBackgroundResource(R.drawable.iinterests_follow_button_background)
                    setTextColor(ContextCompat.getColor(context,R.color.followText))
                }

            } else {
                followingBtn.apply {
                    setBackgroundResource(R.drawable.iinterests_following_button_background)
                    setTextColor(ContextCompat.getColor(context,R.color.white))
                }
            }

            followingBtn.text = item.third

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemInterestesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = interestList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(interestList[position])
    }

    companion object{
        val interestList= listOf(

            Triple(R.drawable.sign_in_sign_up_image,"world","following"),
            Triple(R.drawable.sign_in_sign_up_image,"world","+ follow"),
            Triple(R.drawable.sign_in_sign_up_image,"world","following"),
            Triple(R.drawable.sign_in_sign_up_image,"world","+ follow"),
            Triple(R.drawable.sign_in_sign_up_image,"world","following"),
            Triple(R.drawable.sign_in_sign_up_image,"world","+ follow"),
            Triple(R.drawable.sign_in_sign_up_image,"world","following"),
            Triple(R.drawable.sign_in_sign_up_image,"world","+ follow"),
            Triple(R.drawable.sign_in_sign_up_image,"world","following"),
            Triple(R.drawable.sign_in_sign_up_image,"world","+ follow"),
            Triple(R.drawable.sign_in_sign_up_image,"world","following"),
            Triple(R.drawable.sign_in_sign_up_image,"world","+ follow"),
            Triple(R.drawable.sign_in_sign_up_image,"world","following"),
            Triple(R.drawable.sign_in_sign_up_image,"world","+ follow"),
            Triple(R.drawable.sign_in_sign_up_image,"world","following"),
            Triple(R.drawable.sign_in_sign_up_image,"world","+ follow"),


        )
    }
}