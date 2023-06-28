package com.example.developerstools.arquitecturemvvm.data

import com.example.developerstools.arquitecturemvvm.data.model.QuoteModel
import com.example.developerstools.arquitecturemvvm.data.model.QuoteProvider
import com.example.developerstools.arquitecturemvvm.data.network.QuoteService

class QuoteRepository {

    private val api = QuoteService()

    suspend fun getAllQuotes():List<QuoteModel>{
        val response =  api.getQuotes()
        QuoteProvider.quotes = response
        return response
    }
}