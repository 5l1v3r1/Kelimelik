package com.aydsoftware.kelimelik.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aydsoftware.ekonoware.R
import com.aydsoftware.ekonoware.databinding.FragmentTestBinding
import com.aydsoftware.kelimelik.ui.viewmodel.MainViewModel
import com.aydsoftware.kelimelik.util.DataState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class TestFragment : Fragment(R.layout.fragment_test) {
    private var _binding: FragmentTestBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<TestFragmentArgs>()

    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTestBinding.bind(view)

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
                        buttonNext.setOnClickListener {
                            val input = inputAnswer.text.toString()
                            if (input.isNotEmpty()) {
                                if (input.lowercase() == dataState.data.turkish.lowercase()) {
                                    Snackbar.make(binding.root, "Bravo! Cevap dogru", Snackbar.LENGTH_INDEFINITE)
                                        .setAnchorView(binding.buttonNext)
                                        .setAction("Gec") {
                                            viewModel.getRandomWord()
                                            inputAnswer.setText("")
                                        }
                                        .show()
                                } else {
                                    val action = TestFragmentDirections.actionTestFragmentToMistakeBottomSheet(dataState.data, input)
                                    findNavController().navigate(action)

                                    if (args.mistakeShown){
                                        viewModel.getRandomWord()
                                        inputAnswer.setText("")
                                    }
                                }
                            }
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