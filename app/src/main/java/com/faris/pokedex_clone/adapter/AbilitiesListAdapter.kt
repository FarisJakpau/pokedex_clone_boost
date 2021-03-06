package com.faris.pokedex_clone.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faris.pokedex_clone.network.model.response.AbilityResponseModel
import com.faris.pokedex_clone.util.SingleLiveEvent
import com.faris.pokedex_clone.viewHolder.AbilityViewHolder

/**
 * Created by farisjakpau on 06/03/2021.
 * Pokedex_Clone.
 */

class AbilitiesListAdapter (var abilitiesList: ArrayList<AbilityResponseModel>?) : RecyclerView.Adapter<AbilityViewHolder>() {

    val evenHolder = EvenHolder()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityViewHolder {
        return AbilityViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return abilitiesList?.size ?: 0
    }

    override fun onBindViewHolder(holder: AbilityViewHolder, position: Int) {
        abilitiesList?.get(position)?.let { holder.bindTo(it, evenHolder) }
    }

    class EvenHolder() {
        val onClick: SingleLiveEvent<AbilityResponseModel> = SingleLiveEvent()
    }

}