package com.aydsoftware.kelimelik.repository

import com.aydsoftware.kelimelik.model.Word
import com.aydsoftware.kelimelik.retrofit.WordListApi
import com.aydsoftware.kelimelik.room.WordDao
import com.aydsoftware.kelimelik.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository
constructor(
    private val wordListApi: WordListApi,
    private val wordDao: WordDao
) {
    suspend fun getRandomWord(): Flow<DataState<Word>> =
        flow {
            try {
                emit(DataState.Loading)
                var roomResult = wordDao.getAllWords()
                if (roomResult.isEmpty()) {
                    val retrofitResult = wordListApi.getWordList()
                    retrofitResult.forEach {
                        wordDao.insertWords(it)
                    }
                    roomResult = retrofitResult
                }
                emit(DataState.Success(roomResult.random()))
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }

    suspend fun getWordList(): Flow<DataState<List<Word>>> =
        flow {
            try {
                emit(DataState.Loading)
                var roomResult = wordDao.getAllWords()
                if (roomResult.isEmpty()) {
                    val retrofitResult = wordListApi.getWordList()
                    retrofitResult.forEach {
                        wordDao.insertWords(it)
                    }
                    roomResult = retrofitResult
                }
                emit(DataState.Success(roomResult))
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }

    suspend fun getWordById(id: Int): Flow<DataState<Word>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = wordDao.getWordById(id)
                if (result.isNotEmpty()) {
                    emit(DataState.Success(result[0]))
                }
                emit(DataState.Error(Exception("err_no_such_word_by_id")))
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }

    suspend fun getWordByGermanMeaning(wordInGerman: String): Flow<DataState<Word>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = wordDao.getWordByGermanMeaning(wordInGerman)
                if (result.isNotEmpty()) {
                    emit(DataState.Success(result[0]))
                }
                emit(DataState.Error(Exception("err_no_such_word_by_german_meaning")))
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }

    suspend fun getWordByTurkishMeaning(wordInTurkish: String): Flow<DataState<Word>> =
        flow {
            try {
                emit(DataState.Loading)
                val result = wordDao.getWordByTurkishMeaning(wordInTurkish)
                if (result.isNotEmpty()) {
                    emit(DataState.Success(result[0]))
                }
                emit(DataState.Error(Exception("err_no_such_word_by_turkish_meaning")))
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }

    suspend fun updateWord(word: Word): Flow<DataState<List<Word>>> =
        flow {
            try {
                emit(DataState.Loading)
                wordDao.update(word)
                val result = wordDao.getAllWords()
                emit(DataState.Success(result))
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
}