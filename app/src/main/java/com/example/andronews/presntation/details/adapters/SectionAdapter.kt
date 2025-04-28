package com.example.andronews.presntation.details.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.andronews.R
import com.example.andronews.data.api.news.dto.Comment
import com.example.andronews.data.api.news.dto.News
import com.example.andronews.data.api.news.dto.Section
import com.example.andronews.data.api.news.dto.SectionType
import com.example.andronews.databinding.ItemSectionCommentsBinding
import com.example.andronews.databinding.ItemSectionOtherNewsBinding

class SectionAdapter(
    private val section: List<Section>,
    private val onClickNews: (news: News) -> Unit,
    private val onClickComment: (comment: Comment) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class OtherNewsHolder(private val binding: ItemSectionOtherNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(section: Section) = with(binding) {
            otherNewsTitle.text =
                root.context.getString(R.string.item_other_news_title, section.title)
            otherNewsList.adapter = OtherNewsAdapter(section.otherNew, onClickNews)
        }
    }

    inner class CommentsHolder(private val binding: ItemSectionCommentsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(section: Section) = with(binding) {
            commentsTitle.text =
                root.context.getString(R.string.item_comments_comment_title, section.title)
            commentsCount.text =
                root.context.getString(R.string.item_comments_count, section.commentCount)

            commentsList.adapter = CommentsAdapter(section.comments, onClickComment)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return when (SectionType.entries[viewType]) {

            SectionType.horizontal -> OtherNewsHolder(
                ItemSectionOtherNewsBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )

            SectionType.vertical -> CommentsHolder(
                ItemSectionCommentsBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is OtherNewsHolder -> holder.bind(section[position])
            is CommentsHolder -> holder.bind(section[position])
        }
    }

    override fun getItemCount() = section.size

    override fun getItemViewType(position: Int) = section[position].type.ordinal


}