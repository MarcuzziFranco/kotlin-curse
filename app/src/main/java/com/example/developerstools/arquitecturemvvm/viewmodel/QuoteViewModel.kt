package com.example.developerstools.arquitecturemvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.developerstools.arquitecturemvvm.model.QuoteModel
import com.example.developerstools.arquitecturemvvm.model.QuoteProvider

class QuoteViewModel : ViewModel() {

    var quoteModel = MutableLiveData<QuoteModel>()

    fun randomQuote() {
        val currentQuote = QuoteProvider.random()
        quoteModel.postValue(currentQuote)
    }
}