package com.aydsoftware.kelimelik.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aydsoftware.kelimelik.model.Word
import com.aydsoftware.kelimelik.repository.MainRepository
import com.aydsoftware.kelimelik.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class ReviewViewModel
@Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {
    private val _mistakenWords: MutableLiveData<DataState<List<Word>>> = MutableLiveData()
    val mistakenWords: LiveData<DataState<List<Word>>> get() = _mistakenWords

    fun getMistakenWords() {
        viewModelScope.launch {
            val response = mainRepository.getMistakenWords()
            response.onEach { _mistakenWords.value = it }.launchIn(viewModelScope)
        }
    }
}