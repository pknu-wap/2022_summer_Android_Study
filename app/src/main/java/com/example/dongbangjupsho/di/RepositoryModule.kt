package com.example.dongbangjupsho.di

import com.example.dongbangjupsho.data.repository.FirebaseRepositoryImpl
import com.example.dongbangjupsho.domain.repository.FirebaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideFirebaseRepository(): FirebaseRepository{
        return FirebaseRepositoryImpl()
    }

}