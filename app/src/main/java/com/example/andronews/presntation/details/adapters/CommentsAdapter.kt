package com.example.andronews.presntation.details.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.andronews.R
import com.example.andronews.data.api.news.dto.Comment
import com.example.andronews.databinding.ItemCommentsBinding
import com.example.andronews.util.getTimeAgo

class CommentsAdapter(
    private val comment: List<Comment>,
    private val onClick: (comment: Comment) -> Unit
) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemCommentsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment) = with(binding) {
            Glide.with(root).load(comment.user.avatar).into(avatar)
            userName.text =
                root.context.getString(R.string.item_comments_user_name, comment.user.username)

            commentTitle.text =
                root.context.getString(R.string.item_comments_comment_title, comment.commentText)

            val addTimeAgo = getTimeAgo(comment.addTime)
            commendAddTime.text =
                root.context.getString(R.string.item_comments_comment_add_time, addTimeAgo)

            reply.setOnClickListener {
                onClick(comment)
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(
        ItemCommentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(comment[position])
    }

    override fun getItemCount() = comment.size


}