package com.aydsoftware.kelimelik.di

import com.aydsoftware.kelimelik.repository.MainRepository
import com.aydsoftware.kelimelik.retrofit.WordListApi
import com.aydsoftware.kelimelik.room.WordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        wordListApi: WordListApi,
        wordDao: WordDao
    ): MainRepository = MainRepository(
        wordListApi,
        wordDao
    )
}