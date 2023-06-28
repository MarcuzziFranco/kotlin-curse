package com.example.developerstools.arquitecturemvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.developerstools.arquitecturemvvm.data.model.QuoteModel
import com.example.developerstools.arquitecturemvvm.data.model.QuoteProvider
import com.example.developerstools.arquitecturemvvm.domain.GetQuotesUseCase
import com.example.developerstools.arquitecturemvvm.domain.GetRandomQuoteUseCase
import kotlinx.coroutines.launch

class QuoteViewModel : ViewModel() {

    var quoteModel = MutableLiveData<QuoteModel>()
    var isLoading = MutableLiveData<Boolean>()

    var getQuotesUseCase = GetQuotesUseCase()
    var getRandomQuoteUseCase = GetRandomQuoteUseCase()
    fun onCrate() {
           viewModelScope.launch{
               isLoading.postValue(true)
               val result = getQuotesUseCase()
               if (!result.isNullOrEmpty()){
                   quoteModel.postValue(result[0])
                   isLoading.postValue(false)
               }
           }
    }

    fun randomQuote() {
        isLoading.postValue(true)
        val quote = getRandomQuoteUseCase()
        if(quote != null){
            quoteModel.postValue(quote)
        }
        isLoading.postValue(false)
    }


}