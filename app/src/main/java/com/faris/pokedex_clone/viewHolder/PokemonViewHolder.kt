package com.faris.pokedex_clone.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.faris.pokedex_clone.R
import com.faris.pokedex_clone.databinding.ItemPokemonBinding
import com.faris.pokedex_clone.network.model.response.BaseResponseModel
import com.faris.pokedex_clone.util.getImageLink
import com.faris.pokedex_clone.util.getPokemonId

/**
 * Created by FarisJakpau on 4/03/2021
 *
 **/
class PokemonViewHolder(private val itemPokemonBinding: ItemPokemonBinding) :
    RecyclerView.ViewHolder(itemPokemonBinding.root) {

    companion object {
        fun create(parent: ViewGroup): PokemonViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding: ItemPokemonBinding =
                DataBindingUtil.inflate(inflater, R.layout.item_pokemon, parent, false)
            return PokemonViewHolder(binding)
        }
    }

    fun bindTo(baseResponseModel: BaseResponseModel) {
        itemPokemonBinding.name = baseResponseModel.name

        Glide.with(itemView.context)
                .load(getImageLink(baseResponseModel.url.getPokemonId()))
            .into(itemPokemonBinding.ivPokemon)

    }
}