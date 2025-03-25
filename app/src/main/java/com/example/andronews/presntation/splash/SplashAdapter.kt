package com.example.andronews.presntation.splash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.andronews.R
import com.example.andronews.databinding.ItemSplashBinding

class SplashAdapter : RecyclerView.Adapter<SplashAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ItemSplashBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(page: SplashPage) = with(binding) {
            Glide.with(root.context).load(page.imageRes).into(image)
            splashTitle.text = root.context.getString(page.titleRes)
            splashSubTitle.text = root.context.getString(page.subTitleRes)

            root.setBackgroundResource(page.backgroundRes)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemSplashBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = splashPages.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(splashPages[position])
    }

    companion object {
        data class SplashPage(
            val imageRes: Int,
            val titleRes: Int,
            val subTitleRes: Int,
            val backgroundRes: Int
        )


        val splashPages = listOf(
            SplashPage(
                R.drawable.splash_image_0,
                R.string.splash_title_0,
                R.string.splash_sub_title_0,
                R.drawable.splash_bg_0
            ),
            SplashPage(
                R.drawable.splash_image_1,
                R.string.splash_title_1,
                R.string.splash_sub_title_1,
                R.drawable.splash_bg_1
            ),
        )
    }
}