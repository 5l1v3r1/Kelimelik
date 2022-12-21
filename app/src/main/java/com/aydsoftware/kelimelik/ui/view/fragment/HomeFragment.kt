package com.aydsoftware.kelimelik.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aydsoftware.ekonoware.R
import com.aydsoftware.ekonoware.databinding.FragmentHomeBinding
import com.aydsoftware.kelimelik.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        binding.apply {
            goLearnNewWords.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToLearnWordFragment()
                findNavController().navigate(action)
            }
            goTest.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToTestFragment(false)
                findNavController().navigate(action)
            }
            goReview.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToReviewFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}