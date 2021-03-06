package com.faris.pokedex_clone.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.faris.pokedex_clone.R
import com.faris.pokedex_clone.adapter.AbilitiesListAdapter
import com.faris.pokedex_clone.databinding.ItemAbilityBinding
import com.faris.pokedex_clone.network.model.response.AbilityResponseModel

/**
 * Created by farisjakpau on 06/03/2021.
 * Pokedex_Clone.
 */

class AbilityViewHolder (private val itemAbilityBinding: ItemAbilityBinding) :RecyclerView.ViewHolder(itemAbilityBinding.root) {
    companion object {
        fun create(parent: ViewGroup): AbilityViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding: ItemAbilityBinding =
                DataBindingUtil.inflate(inflater, R.layout.item_ability, parent, false)
            return AbilityViewHolder(binding)
        }
    }

    fun bindTo(ability: AbilityResponseModel, evenHolder:AbilitiesListAdapter.EvenHolder) {
        itemAbilityBinding.ability = ability.ability?.name
        itemAbilityBinding.isHidden = ability.is_hidden

        itemAbilityBinding.llRoot.setOnClickListener {
            evenHolder.onClick.postValue(ability)
        }
    }
}