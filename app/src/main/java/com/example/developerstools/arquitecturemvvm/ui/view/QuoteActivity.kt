package com.example.developerstools.arquitecturemvvm.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.developerstools.arquitecturemvvm.ui.viewmodel.QuoteViewModel
import com.example.developerstools.databinding.ActivityQuoteBinding

class QuoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuoteBinding

    private val quoteViewModel: QuoteViewModel by viewModels() //connect viewModel with view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuoteBinding.inflate(layoutInflater)

        setContentView(binding.root)

        quoteViewModel.onCrate()

        quoteViewModel.quoteModel.observe(this, Observer {currentQuote->
            binding.tvQuote.text = currentQuote.quote
            binding.tvAuthor.text = currentQuote.author
        })

        quoteViewModel.isLoading.observe(this, Observer { loading->
            binding.pbLoading.isVisible = loading
        })

        binding.viewContainer.setOnClickListener{
            quoteViewModel.randomQuote()
        }
    }
}