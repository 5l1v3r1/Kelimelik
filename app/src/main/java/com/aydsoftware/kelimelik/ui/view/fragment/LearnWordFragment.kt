package com.aydsoftware.kelimelik.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aydsoftware.ekonoware.R
import com.aydsoftware.ekonoware.databinding.FragmentHomeBinding
import com.aydsoftware.ekonoware.databinding.FragmentLearnWordBinding
import com.aydsoftware.kelimelik.ui.viewmodel.MainViewModel
import com.aydsoftware.kelimelik.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class LearnWordFragment : Fragment(R.layout.fragment_learn_word) {
    private var _binding: FragmentLearnWordBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLearnWordBinding.bind(view)

        binding.apply {
            buttonError.setOnClickListener {
                viewModel.getRandomWord()
            }
            buttonNext.setOnClickListener {
                viewModel.getRandomWord()
            }
        }
        setObservers()
        viewModel.getRandomWord()
    }

    private fun setObservers() {
        viewModel.randomWord.observe(viewLifecycleOwner) { dataState ->
            binding.apply {
                when (dataState) {
                    is DataState.Success -> {
                        progressBar.visibility = View.GONE
                        buttonError.visibility = View.GONE
                        textError.visibility = View.GONE
                        wordLayout.visibility = View.VISIBLE
                        buttonNext.visibility = View.VISIBLE

                        textInGerman.text = "${dataState.data.article} ${dataState.data.german}"
                        textInTurkish.text = dataState.data.turkish
                        textGender.text = when(dataState.data.article) {
                            "der" -> "masculine (der)"

                            "die" -> "feminine (die)"

                            "das" -> "neuter (das)"

                            else -> ""
                        }
                    }
                    is DataState.Error -> {
                        progressBar.visibility = View.GONE
                        buttonError.visibility = View.VISIBLE
                        textError.visibility = View.VISIBLE
                        wordLayout.visibility = View.GONE
                        buttonNext.visibility = View.GONE
                        println(dataState.exception)
                    }
                    is DataState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                        buttonError.visibility = View.GONE
                        textError.visibility = View.GONE
                        wordLayout.visibility = View.VISIBLE
                        buttonNext.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}