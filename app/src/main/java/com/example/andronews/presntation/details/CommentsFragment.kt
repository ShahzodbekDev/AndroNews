package com.example.andronews.presntation.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.andronews.R
import com.example.andronews.data.api.news.dto.Comment
import com.example.andronews.databinding.FragmentCommentsBinding
import com.example.andronews.presntation.details.adapters.CommentsAdapter
import com.example.andronews.presntation.details.viewModels.DetailViewModel
import com.example.andronews.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsFragment : BaseFragment<FragmentCommentsBinding>(FragmentCommentsBinding::inflate) {

    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<CommentsFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ids = args.id
        viewModel.getDetail(ids)
        viewModel.getComments(ids)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() = with(binding) {

        viewModel.loading.observe(viewLifecycleOwner) {
            loading.root.isVisible = it
        }
        viewModel.error.observe(viewLifecycleOwner) {
            erorr.root.isVisible = it
        }
        viewModel.detail.observe(viewLifecycleOwner) {
            Glide.with(root).load(it.image).into(image)

            categoryTitle.text = getString(R.string.news_category_title, it.category)

            newsTitle.text = getString(R.string.news_title, it.title)
        }
        viewModel.comments.observe(viewLifecycleOwner) {
            commentsList.adapter = CommentsAdapter(it, ::onClickComment)
            commentCount.text = getString(R.string.news_comment_count, it.size)
        }

    }

    private fun initUi() = with(binding) {
        back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun onClickComment(comment: Comment) {

    }

}
