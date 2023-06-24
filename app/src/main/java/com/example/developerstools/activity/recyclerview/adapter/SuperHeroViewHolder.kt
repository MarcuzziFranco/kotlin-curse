package com.example.developerstools.activity.recyclerview.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.developerstools.activity.recyclerview.SuperHero
import com.example.developerstools.databinding.ItemSuperheroBinding

class SuperHeroViewHolder(view: View) : ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun render(
        superHeroModel: SuperHero,
        onClickListener: (SuperHero) -> Unit,
        onClickDelete: (Int) -> Unit
    ) {
        binding.tvSuperHeroName.text = superHeroModel.superHero
        binding.tvRealName.text = superHeroModel.realName
        binding.tvPublisher.text = superHeroModel.publisher
        Glide.with(binding.ivSuperHero.context).load(superHeroModel.photo).into(binding.ivSuperHero)

        //Onclick in image
        binding.ivSuperHero.setOnClickListener {
            Toast.makeText(binding.ivSuperHero.context, superHeroModel.realName, Toast.LENGTH_SHORT)
                .show()
        }

        //Onclick in complete component
        itemView.setOnClickListener{
            onClickListener(superHeroModel)
        }

        binding.btDelete.setOnClickListener { onClickDelete(adapterPosition) }
    }
}