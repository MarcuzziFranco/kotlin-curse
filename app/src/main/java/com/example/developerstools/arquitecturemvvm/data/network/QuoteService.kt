package com.example.developerstools.arquitecturemvvm.data.network

import com.example.developerstools.arquitecturemvvm.core.RetrofitHelper
import com.example.developerstools.arquitecturemvvm.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteService @Inject constructor(private val api:IQuoteApiClient) {

    //private val retrofit=RetrofitHelper.getRetrofit()

    suspend fun getQuotes():List<QuoteModel>{
        return withContext(Dispatchers.IO){
            val response = api.getAllQuotes()
            response.body() ?: emptyList()
        }
    }
}