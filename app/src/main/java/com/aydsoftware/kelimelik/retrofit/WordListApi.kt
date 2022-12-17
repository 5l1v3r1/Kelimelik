package com.aydsoftware.kelimelik.retrofit

import com.aydsoftware.kelimelik.model.Word
import retrofit2.http.GET

interface WordListApi {
    @GET("ch3xx/Kelimelik/main/wordlist.json")
    suspend fun getWordList(): List<Word>
}