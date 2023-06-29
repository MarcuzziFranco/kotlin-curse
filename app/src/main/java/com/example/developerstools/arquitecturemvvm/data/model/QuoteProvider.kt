package com.example.developerstools.arquitecturemvvm.data.model

import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class QuoteProvider @Inject constructor() {
    //companion object {
        var quotes:List<QuoteModel> = emptyList()
    //}
}