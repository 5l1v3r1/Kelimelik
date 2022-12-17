package com.aydsoftware.kelimelik.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aydsoftware.ekonoware.R
import com.aydsoftware.ekonoware.databinding.BottomSheetMistakeBinding
import com.aydsoftware.kelimelik.model.Word
import com.aydsoftware.kelimelik.ui.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class MistakeBottomSheet : BottomSheetDialogFragment() {
    private var _binding: BottomSheetMistakeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()

    private val args by navArgs<MistakeBottomSheetArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet_mistake, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = BottomSheetMistakeBinding.bind(view)
        binding.apply {
            textInGerman.text = "${args.word.article} ${args.word.german}"
            textInTurkish.text = "Dogrusu: ${args.word.turkish} (${args.mistake})"
            buttonOk.setOnClickListener {
                viewModel.updateWord(Word(args.word.uid, args.word.article, args.word.german, args.word.turkish, true))
                val action = MistakeBottomSheetDirections.actionMistakeBottomSheetToTestFragment(true)
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}