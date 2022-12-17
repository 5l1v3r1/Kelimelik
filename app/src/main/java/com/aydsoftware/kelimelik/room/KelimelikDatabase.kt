package com.aydsoftware.kelimelik.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aydsoftware.kelimelik.model.Word

@Database(entities = [Word::class], version = 1)
abstract class KelimelikDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}