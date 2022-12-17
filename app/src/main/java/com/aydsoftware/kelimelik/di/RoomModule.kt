package com.aydsoftware.kelimelik.di

import android.content.Context
import androidx.room.Room
import com.aydsoftware.kelimelik.room.KelimelikDatabase
import com.aydsoftware.kelimelik.room.WordDao
import com.aydsoftware.kelimelik.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideKelimelikDatabase(@ApplicationContext context: Context): KelimelikDatabase =
        Room.databaseBuilder(
            context,
            KelimelikDatabase::class.java,
            Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideWordDao(kelimelikDatabase: KelimelikDatabase): WordDao =
        kelimelikDatabase.wordDao()
}