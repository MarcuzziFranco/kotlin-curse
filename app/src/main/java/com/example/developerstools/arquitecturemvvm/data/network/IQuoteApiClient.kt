package com.example.developerstools.arquitecturemvvm.data.network

import com.example.developerstools.arquitecturemvvm.data.model.QuoteModel
import retrofit2.Response
import retrofit2.http.GET

interface IQuoteApiClient {
    @GET("/quotes.json")
    suspend fun getAllQuotes():Response<List<QuoteModel>>
}