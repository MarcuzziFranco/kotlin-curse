package com.example.developerstools.arquitecturemvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.developerstools.arquitecturemvvm.viewmodel.QuoteViewModel
import com.example.developerstools.databinding.ActivityQuoteBinding

class QuoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuoteBinding

    private val quoteViewModel:QuoteViewModel by viewModels() //connect viewModel with view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuoteBinding.inflate(layoutInflater)

        setContentView(binding.root)

        quoteViewModel.quoteModel.observe(this, Observer {currentQuote->
            binding.tvQuote.text = currentQuote.quote
            binding.tvAuthor.text = currentQuote.author
        })

        binding.viewContainer.setOnClickListener{
            quoteViewModel.randomQuote()
        }
    }
}