package com.aydsoftware.kelimelik.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aydsoftware.kelimelik.model.Word

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWords(word: Word)

    @Query("SELECT * FROM words")
    suspend fun getAllWords(): List<Word>

    @Query("SELECT * FROM words WHERE mistake == :mistake")
    suspend fun getMistakenWords(mistake: Boolean = true): List<Word>

    @Query("SELECT * FROM words WHERE uid == :id")
    suspend fun getWordById(id: Int): List<Word>

    @Query("SELECT * FROM words WHERE german == :wordInGerman")
    suspend fun getWordByGermanMeaning(wordInGerman: String): List<Word>

    @Query("SELECT * FROM words WHERE turkish == :wordInTurkish")
    suspend fun getWordByTurkishMeaning(wordInTurkish: String): List<Word>

    @Update
    suspend fun update(word: Word)
}