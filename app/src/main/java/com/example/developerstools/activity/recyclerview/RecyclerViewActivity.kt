package com.example.developerstools.activity.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.developerstools.activity.BaseActivity
import com.example.developerstools.activity.recyclerview.adapter.SuperHeroAdapter
import com.example.developerstools.databinding.ActivityRecyclerViewBinding
import kotlin.random.Random

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBinding
    private var superherosMutableList = SuperHeroProvider.superheroList
    private lateinit var adapter: SuperHeroAdapter
    private var llmanager: LinearLayoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initRecycleView()
        configListenerCreateBt()
        configListenerFilterRv()
    }

    private fun createSuperHero() {
        val idHero = Random.nextInt(10000).toString()
        val superHero = SuperHero(
            "Incognito",
            "Yo mismo",
            "SR X",
            "https://pbs.twimg.com/profile_images/1012362101510160384/EjayQ10E_400x400.jpg",
            idHero

        )

        superherosMutableList = superherosMutableList.plus(superHero)
        //adapter.notifyItemInserted(3)
        //llmanager.scrollToPositionWithOffset(3, 20)
        adapter.updateSuperHero(superherosMutableList)
    }

    private fun initRecycleView() {

        adapter = SuperHeroAdapter(
            superherosMutableList,
            ::onItemSelected,
            ::onDeleted
        )
        //val manager = LinearLayoutManager(this)
        //val manager  = GridLayoutManager(this,2)
        val decoration = DividerItemDecoration(
            this,
            llmanager.orientation
        ) //Add line divider between items recyclerview

        binding.rvsuperhero.layoutManager = llmanager
        binding.rvsuperhero.adapter = adapter

        binding.rvsuperhero.addItemDecoration(decoration) //Add line divide between items recycler view.
    }

    private fun onItemSelected(superHero: SuperHero) {
        Toast.makeText(binding.root.context, superHero.superHero, Toast.LENGTH_SHORT).show()
    }

    private fun onDeleted(position: Int) {
        binding.etFilter.text.clear()
        binding.etFilter.clearFocus()

        //superherosMutableList.removeAt(position)
        superherosMutableList = superherosMutableList.minus(superherosMutableList[position])
        //adapter.notifyItemRemoved(position)
        adapter.updateSuperHero(superherosMutableList)

    }

    private fun configListenerCreateBt(){
        binding.btnNewSuperHero.setOnClickListener { createSuperHero() }
    }

    private fun configListenerFilterRv(){
        binding.etFilter.addTextChangedListener { userFilter ->
            val superheroFiltered =
                superherosMutableList.filter { superHero ->
                    superHero.superHero.lowercase().contains(userFilter.toString().lowercase())
                }
            adapter.updateSuperHero(superheroFiltered)
        }
    }
}