package com.example.developerstools.activity.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.developerstools.R
import com.example.developerstools.activity.recyclerview.SuperHero

class SuperHeroAdapter(
    private var superheroList: List<SuperHero>,
    private val onClickListener: (SuperHero) -> Unit,
    private val onClickDelete:(Int)-> Unit
) : RecyclerView.Adapter<SuperHeroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    override fun getItemCount(): Int = superheroList.size

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val item = superheroList[position]
        holder.render(item, onClickListener,onClickDelete)
    }

    fun updateSuperHero(newListSuperHero: List<SuperHero>){

        val superHeroDiffUtils = SuperHeroDiffUtil(superheroList,newListSuperHero)
        val result = DiffUtil.calculateDiff(superHeroDiffUtils)
        superheroList = newListSuperHero
        result.dispatchUpdatesTo(this)

    }
}