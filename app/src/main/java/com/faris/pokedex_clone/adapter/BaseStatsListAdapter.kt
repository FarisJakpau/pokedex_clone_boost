package com.faris.pokedex_clone.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faris.pokedex_clone.network.model.response.StatResponseModel
import com.faris.pokedex_clone.viewHolder.BaseStatsViewHolder

/**
 * Created by FarisJakpau on 8/03/2021
 *
 **/
class BaseStatsListAdapter(var baseStatList: ArrayList<StatResponseModel>?) : RecyclerView.Adapter<BaseStatsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseStatsViewHolder {
        return BaseStatsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: BaseStatsViewHolder, position: Int) {
        baseStatList?.get(position)?.let { holder.bindTo(it) }
    }

    override fun getItemCount(): Int {
        return baseStatList?.size ?: 0
    }
}