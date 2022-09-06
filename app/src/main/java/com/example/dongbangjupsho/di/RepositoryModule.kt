package com.example.dongbangjupsho.di

import android.content.SharedPreferences
import com.example.dongbangjupsho.data.repository.FirebaseAuthRepositoryImpl
import com.example.dongbangjupsho.data.repository.FirebaseDatabaseRepositoryImpl
import com.example.dongbangjupsho.domain.repository.FirebaseAuthRepository
import com.example.dongbangjupsho.domain.repository.FirebaseDatabaseRepository
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
    fun provideFirebaseRepository(prefs: SharedPreferences): FirebaseAuthRepository{
        return FirebaseAuthRepositoryImpl(prefs)
    }

    @Provides
    @Singleton
    fun provideFirebaseDatabaseRepository(): FirebaseDatabaseRepository{
        return FirebaseDatabaseRepositoryImpl()
    }

}