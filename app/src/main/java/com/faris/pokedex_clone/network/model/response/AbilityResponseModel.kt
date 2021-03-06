package com.faris.pokedex_clone.network.model.response

/**
 * Created by FarisJakpau on 4/03/2021
 *
 **/
data class AbilityResponseModel(
        val ability: BaseResponseModel?,
        val is_hidden: Boolean?,
        val slot: Int?
)

data class AbilityDetailResponseModel(
        val effect_changes: List<EffectEntriesMainResponseModel>?,
        val effect_entries: List<EffectEntriesResponseModel>?,
        val flavor_text_entries: List<FlavourTextEntriesResponseModel>?,
        val name: String?
)
