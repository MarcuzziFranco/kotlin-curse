package com.example.developerstools.arquitecturemvvm.data

import com.example.developerstools.arquitecturemvvm.data.model.QuoteModel
import com.example.developerstools.arquitecturemvvm.data.model.QuoteProvider
import com.example.developerstools.arquitecturemvvm.data.network.QuoteService
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteProvider: QuoteProvider
) {

    //private val api = QuoteService()

    suspend fun getAllQuotes(): List<QuoteModel> {
        val response = api.getQuotes()
        quoteProvider.quotes = response
        return response
    }
}