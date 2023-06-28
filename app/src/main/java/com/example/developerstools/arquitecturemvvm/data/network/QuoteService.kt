package com.example.developerstools.arquitecturemvvm.data.network

import com.example.developerstools.arquitecturemvvm.core.RetrofitHelper
import com.example.developerstools.arquitecturemvvm.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteService {

    private val retrofit=RetrofitHelper.getRetrofit()

    suspend fun getQuotes():List<QuoteModel>{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(IQuoteApiClient::class.java).getAllQuotes()
            response.body() ?: emptyList()
        }
    }
}