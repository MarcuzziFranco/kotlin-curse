package com.example.developerstools.arquitecturemvvm.di

import com.example.developerstools.arquitecturemvvm.data.network.IQuoteApiClient
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
            .baseUrl("https://realtimedatabasejson-47623-default-rtdb.firebaseio.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideIQuoteApiClient(retrofit: Retrofit):IQuoteApiClient{
        return retrofit.create(IQuoteApiClient::class.java)
    }

}