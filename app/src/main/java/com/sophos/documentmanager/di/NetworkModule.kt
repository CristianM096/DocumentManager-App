package com.sophos.documentmanager.di

import androidx.fragment.app.Fragment
import com.sophos.documentmanager.data.network.FragmentService
import com.sophos.documentmanager.data.network.FragmentService_Factory
import com.sophos.documentmanager.data.network.UserApiClient
import com.sophos.documentmanager.domain.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://6w33tkx4f9.execute-api.us-east-1.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideUserApiClient(retrofit: Retrofit):UserApiClient{
        return retrofit.create(UserApiClient::class.java)
    }


}