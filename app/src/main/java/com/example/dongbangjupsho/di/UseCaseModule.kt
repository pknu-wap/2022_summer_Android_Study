package com.example.dongbangjupsho.di

import com.example.dongbangjupsho.domain.use_case.validate.ValidateEmail
import com.example.dongbangjupsho.domain.use_case.validate.ValidateNickName
import com.example.dongbangjupsho.domain.use_case.validate.ValidatePassword
import com.example.dongbangjupsho.domain.use_case.validate.ValidateRepeatedPassword
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideValidateEmail(): ValidateEmail{
        return ValidateEmail()
    }

    @Provides
    @Singleton
    fun provideValidateNickName(): ValidateNickName{
        return ValidateNickName()
    }

    @Provides
    @Singleton
    fun provideValidatePassword(): ValidatePassword{
        return ValidatePassword()
    }

    @Provides
    @Singleton
    fun provideValidateRepeatedPassword(): ValidateRepeatedPassword{
        return ValidateRepeatedPassword()
    }

}