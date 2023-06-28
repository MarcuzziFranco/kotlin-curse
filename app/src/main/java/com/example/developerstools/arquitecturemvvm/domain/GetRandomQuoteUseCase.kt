package com.example.developerstools.arquitecturemvvm.domain

import com.example.developerstools.arquitecturemvvm.data.model.QuoteModel
import com.example.developerstools.arquitecturemvvm.data.model.QuoteProvider

class GetRandomQuoteUseCase {

    operator fun invoke(): QuoteModel? {
        val quotes = QuoteProvider.quotes
        if (!quotes.isNullOrEmpty()) {
            val randomNumber = (quotes.indices).random()
            return quotes[randomNumber]
        }
        return null
    }
}