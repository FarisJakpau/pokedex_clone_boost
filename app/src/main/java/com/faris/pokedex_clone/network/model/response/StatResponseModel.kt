package com.faris.pokedex_clone.network.model.response

/**
 * Created by FarisJakpau on 4/03/2021
 *
 **/
data class StatResponseModel(
        val base_stat: Int?,
        val effort: Int?,
        val stat: BaseResponseModel?
)

data class TypeResponseModel(
        val slot: Int?,
        val type: BaseResponseModel?
)