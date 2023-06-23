package com.example.developerstools.activity.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.developerstools.activity.recyclerview.adapter.SuperHeroAdapter
import com.example.developerstools.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycleView()
    }

    private fun initRecycleView(){

        val manager = LinearLayoutManager(this)
        //val manager  = GridLayoutManager(this,2)
        val decoration = DividerItemDecoration(this,manager.orientation) //Add line divider between items recyclerview

        binding.rvsuperhero.layoutManager = manager
        binding.rvsuperhero.adapter = SuperHeroAdapter(SuperHeroProvider.superheroList,::onItemSelected)

        binding.rvsuperhero.addItemDecoration(decoration) //Add line divide between items recycler view.
    }

    private fun onItemSelected(superHero:SuperHero){
        Toast.makeText(binding.root.context,superHero.superHero,Toast.LENGTH_SHORT).show()
    }
}