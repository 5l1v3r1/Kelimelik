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
class MainViewModel
@Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {
    private val _words: MutableLiveData<DataState<List<Word>>> = MutableLiveData()
    val words: LiveData<DataState<List<Word>>> get() = _words

    private val _randomWord: MutableLiveData<DataState<Word>> = MutableLiveData()
    val randomWord: LiveData<DataState<Word>> get() = _randomWord

    private val _selectedWord: MutableLiveData<DataState<Word>> = MutableLiveData()
    val selectedWord: LiveData<DataState<Word>> get() = _selectedWord

    fun getRandomWord() {
        viewModelScope.launch {
            val response = mainRepository.getRandomWord()
            response.onEach { _randomWord.value = it }.launchIn(viewModelScope)
        }
    }

    fun getAllWords() {
        viewModelScope.launch {
            val response = mainRepository.getWordList()
            response.onEach { _words.value = it }.launchIn(viewModelScope)
        }
    }

    fun getWordById(id: Int) {
        viewModelScope.launch {
            val response = mainRepository.getWordById(id)
            response.onEach { _selectedWord.value = it }.launchIn(viewModelScope)
        }
    }

    fun getWordByGermanMeaning(wordInGerman: String) {
        viewModelScope.launch {
            val response = mainRepository.getWordByGermanMeaning(wordInGerman)
            response.onEach { _selectedWord.value = it }.launchIn(viewModelScope)
        }
    }

    fun getWordByTurkishMeaning(wordInTurkish: String) {
        viewModelScope.launch {
            val response = mainRepository.getWordByTurkishMeaning(wordInTurkish)
            response.onEach { _selectedWord.value = it }.launchIn(viewModelScope)
        }
    }

    fun updateWord(word: Word) {
        viewModelScope.launch {
            val response = mainRepository.updateWord(word)
            response.onEach { _words.value = it }.launchIn(viewModelScope)
        }
    }
}