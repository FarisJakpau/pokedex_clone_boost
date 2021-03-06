package com.faris.pokedex_clone.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faris.pokedex_clone.network.model.response.BaseResponseModel
import com.faris.pokedex_clone.util.EventHolder
import com.faris.pokedex_clone.viewHolder.PokemonViewHolder

/**
 * Created by FarisJakpau on 4/03/2021
 *
 **/
class PokemonListAdapter(var pokemonList: ArrayList<BaseResponseModel>?) :
        RecyclerView.Adapter<PokemonViewHolder>() {

    val evenHolder = EventHolder<BaseResponseModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        pokemonList?.get(position)?.let { holder.bindTo(it, evenHolder) }
    }

    override fun getItemCount(): Int {
        return pokemonList?.size ?: 0
    }

    fun updateList(pokemonList: List<BaseResponseModel>?) {
        val tempSize = this.pokemonList?.size ?: 0
        pokemonList?.let {
            this.pokemonList?.addAll(it)
            notifyItemRangeChanged(tempSize, tempSize + it.size)
        }
    }
}