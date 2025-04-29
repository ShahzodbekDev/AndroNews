package com.example.andronews.presntation.details

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ShareCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.andronews.R
import com.example.andronews.common.Constants
import com.example.andronews.data.api.news.dto.Comment
import com.example.andronews.data.api.news.dto.News
import com.example.andronews.databinding.FragmentDetailBinding
import com.example.andronews.presntation.details.adapters.CommentsAdapter
import com.example.andronews.presntation.details.adapters.OtherNewsAdapter
import com.example.andronews.presntation.details.adapters.SectionAdapter
import com.example.andronews.presntation.details.viewModels.DetailViewModel
import com.example.andronews.util.BaseFragment
import com.example.andronews.util.getTimeAgo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getDetail(args.id)
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
            error.root.isVisible = it
        }
        viewModel.detail.observe(viewLifecycleOwner) {

            Glide.with(image.context).load(it.image).into(image)

            val iconRes = if (it.isSave) {
                R.drawable.ic_save_chacked
            } else {
                R.drawable.ic_save
            }
            save.setIconResource(iconRes)

            categoryName.text = getString(R.string.details_category_name, it.category)

            newsTitle.text = getString(R.string.details_news_title, it.title)

            newsDescription.text = getString(R.string.details_news_description, it.description)

            val addTime = getTimeAgo(it.addTime)
            newsAddTime.text = getString(R.string.news_add_time, addTime)

            viewUsers.text = getString(R.string.news_view_users, it.viewsCount)

            section.adapter = SectionAdapter(
                it.section,
                ::onClickNews,
                ::onClickComment
            )

        }
    }

    private fun initUi() = with(binding) {
        back.setOnClickListener {
            findNavController().popBackStack()
        }

        share.setOnClickListener {
            ShareCompat.IntentBuilder(requireContext())
                .setType("text/plain")
                .setChooserTitle(R.string.detail_share)
                .setText(Constants.HOST + "news/${args.id}")
                .startChooser()
        }

        allComments.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.toCommentsFragment(args.id))
        }

        leaveComment.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.toLeaveCommentFragment(args.id))
        }

    }

    private fun onClickNews(news: News) {
        findNavController().navigate(DetailFragmentDirections.actionDetailFragmentSelf(news.id))
    }

    private fun onClickComment(comment: Comment) {
        findNavController().navigate(DetailFragmentDirections.toReplyCommentFragment(comment.id))
    }

}

