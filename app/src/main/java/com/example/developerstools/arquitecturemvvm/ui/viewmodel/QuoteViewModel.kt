package com.example.developerstools.arquitecturemvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.developerstools.arquitecturemvvm.data.model.QuoteModel
import com.example.developerstools.arquitecturemvvm.data.model.QuoteProvider
import com.example.developerstools.arquitecturemvvm.domain.GetQuotesUseCase
import com.example.developerstools.arquitecturemvvm.domain.GetRandomQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotesUseCase:GetQuotesUseCase,
    private val getRandomQuoteUseCase:GetRandomQuoteUseCase
) : ViewModel() {

    var quoteModel = MutableLiveData<QuoteModel>()
    var isLoading = MutableLiveData<Boolean>()

    //var getQuotesUseCase = GetQuotesUseCase()
    //var getRandomQuoteUseCase = GetRandomQuoteUseCase()

    fun onCrate() {
           viewModelScope.launch(Dispatchers.IO){
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