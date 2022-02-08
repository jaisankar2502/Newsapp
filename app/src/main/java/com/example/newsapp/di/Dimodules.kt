package com.example.newsapp.di

import android.app.Application
import com.example.newsapp.NewsRepository
import com.example.newsapp.retrofit.NewsInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.*
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object Dimodules {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun provideNewaInreface(retrofit: Retrofit):NewsInterface{
        return retrofit.create(NewsInterface::class.java)

    }
    @Singleton
    @Provides
    fun provideRepository(newsInterface: NewsInterface):NewsRepository{
        return NewsRepository(newsInterface)
    }
}