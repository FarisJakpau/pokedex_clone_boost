package com.faris.pokedex_clone.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.faris.pokedex_clone.R
import com.faris.pokedex_clone.databinding.ItemBaseStatBinding
import com.faris.pokedex_clone.network.model.response.StatResponseModel

/**
 * Created by FarisJakpau on 8/03/2021
 *
 **/
class BaseStatsViewHolder(private val itemBaseStatBinding: ItemBaseStatBinding) : RecyclerView.ViewHolder(itemBaseStatBinding.root) {
    companion object {
        fun create(parent: ViewGroup): BaseStatsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding: ItemBaseStatBinding =
                    DataBindingUtil.inflate(inflater, R.layout.item_base_stat, parent, false)
            return BaseStatsViewHolder(binding)
        }
    }

    fun bindTo(stat: StatResponseModel) {
        itemBaseStatBinding.statModel = stat
    }
}