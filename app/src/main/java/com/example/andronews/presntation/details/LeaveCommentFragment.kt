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

@AndroidEntryPoint
class LeaveCommentFragment :
    BaseFragment<FragmentLeaveCommentBinding>(FragmentLeaveCommentBinding::inflate) {

    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<LeaveCommentFragmentArgs>()

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
        viewModel.sendProgress.observe(viewLifecycleOwner) {
            progressBar.isVisible = it
            leaveComment.text =
                if (it) null else getString(R.string.fragment_leave_comment_leave)
        }

        viewModel.events.observe(viewLifecycleOwner) {
            when (it) {
                DetailViewModel.Event.ConnectionError -> toast(R.string.connection_error)
                DetailViewModel.Event.Error -> toast(R.string.error)
                DetailViewModel.Event.InvalidCredentials -> toast(R.string.invalid_credentials)
            }
        }

        viewModel.detail.observe(viewLifecycleOwner) {
            Glide.with(root).load(it.image).into(image)
            categoryTitle.text =
                getString(R.string.fragment_leave_comment_category_title, it.category)
            newsTitle.text = getString(R.string.fragment_leave_comment_news_title, it.title)
        }
        viewModel.comments.observe(viewLifecycleOwner) {
            commentCount.text = getString(R.string.fragment_leave_comment_comment_count, it.size)
        }
    }


    private fun initUi() = with(binding) {
        back.setOnClickListener {
            findNavController().popBackStack()
        }

        leaveComment.setOnClickListener {
            viewModel.addComment(args.id, commentInput.text.toString())
        }

        leaveComment.isEnabled = false
        leaveComment.background = ContextCompat.getDrawable(requireContext(),R.drawable.button_inactive)

        commentInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val isTextNotEmpty = !s.isNullOrBlank()

                leaveComment.isEnabled = isTextNotEmpty
                leaveComment.background = ContextCompat.getDrawable(
                    requireContext(),
                    if (isTextNotEmpty) R.drawable.red_btn_background else R.drawable.button_inactive
                )
            }
        })

    }

}


