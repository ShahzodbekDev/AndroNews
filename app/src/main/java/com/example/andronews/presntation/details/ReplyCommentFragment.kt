package com.example.andronews.presntation.details

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.andronews.R
import com.example.andronews.databinding.FragmentLeaveCommentBinding
import com.example.andronews.presentation.sign_in.SignInViewModel
import com.example.andronews.presntation.details.adapters.CommentsAdapter
import com.example.andronews.presntation.details.viewModels.DetailViewModel
import com.example.andronews.util.BaseFragment
import com.example.andronews.util.toast
import dagger.hilt.android.AndroidEntryPoint
import android.text.TextWatcher
import com.example.andronews.databinding.FragmentReplyCommentBinding
import com.example.andronews.util.getTimeAgo

@AndroidEntryPoint
class ReplyCommentFragment :
    BaseFragment<FragmentReplyCommentBinding>(FragmentReplyCommentBinding::inflate) {

    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<ReplyCommentFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getSingleComment("fvdvfb", args.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        subscribeToLiveData()

    }

    private fun subscribeToLiveData() = with(binding) {
        viewModel.sendProgress.observe(viewLifecycleOwner) {
            progressBar.isVisible = it
            replyComment.text =
                if (it) null else getString(R.string.fragment_reply_comment_reply)
        }

        viewModel.events.observe(viewLifecycleOwner) {
            when (it) {
                DetailViewModel.Event.ConnectionError -> toast(R.string.connection_error)
                DetailViewModel.Event.Error -> toast(R.string.error)
                DetailViewModel.Event.InvalidCredentials -> toast(R.string.invalid_credentials)
            }
        }

        viewModel.singleComment.observe(viewLifecycleOwner) {
            Glide.with(root).load(it.user.avatar).into(avatar)
            userName.text =
                getString(R.string.fragment_reply_comment_user_name, it.user.username)
            commentTitle.text = getString(R.string.fragment_reply_comment_comment_title, it.commentText)

            val addTimeAgo = getTimeAgo(it.addTime)
            commendAddTime.text = getString(R.string.fragment_reply_comment_comment_add_time, addTimeAgo)
        }
    }


    private fun initUi() = with(binding) {
        back.setOnClickListener {
            findNavController().popBackStack()
        }

        replyComment.setOnClickListener {

        }

        replyComment.isEnabled = false
        replyComment.background = ContextCompat.getDrawable(requireContext(),R.drawable.button_inactive)

        commentInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val isTextNotEmpty = !s.isNullOrBlank()

                replyComment.isEnabled = isTextNotEmpty
                replyComment.background = ContextCompat.getDrawable(
                    requireContext(),
                    if (isTextNotEmpty) R.drawable.red_btn_background else R.drawable.button_inactive
                )
            }
        })

    }

}


