package com.aydsoftware.kelimelik.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aydsoftware.ekonoware.R
import com.aydsoftware.ekonoware.databinding.FragmentReviewBinding
import com.aydsoftware.kelimelik.ui.viewmodel.ReviewViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class ReviewFragment : Fragment(R.layout.fragment_review) {
    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ReviewViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentReviewBinding.bind(view)
        binding.apply {

        }

        subscribeObservers()
        viewModel.getMistakenWords()
    }

    private fun subscribeObservers() {
        viewModel.mistakenWords.observe(viewLifecycleOwner) {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}