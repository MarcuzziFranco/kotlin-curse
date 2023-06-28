package com.example.developerstools.arquitecturemvvm.domain

import com.example.developerstools.arquitecturemvvm.data.QuoteRepository
import com.example.developerstools.arquitecturemvvm.data.model.QuoteModel
import com.example.developerstools.arquitecturemvvm.data.model.QuoteProvider

class GetQuotesUseCase {

    private  val repository = QuoteRepository()

    //The operator invoke avoid use the method name
    suspend  operator fun invoke():List<QuoteModel>? {
        return repository.getAllQuotes()
    }
}