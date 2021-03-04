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