package com.faris.pokedex_clone.network.model.response

/**
 * Created by farisjakpau on 06/03/2021.
 * Pokedex_Clone.
 */

data class EffectEntriesResponseModel(
    val effect: String,
    val language: BaseResponseModel?,
    val short_effect: String?
)

data class EffectEntriesMainResponseModel(
    val effect_entries: List<EffectEntriesResponseModel>
)

data class FlavourTextEntriesResponseModel(
    val flavor_text: String?,
    val language: BaseResponseModel?,
)