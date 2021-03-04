package com.faris.pokedex_clone.network.model.response

/**
 * Created by FarisJakpau on 4/03/2021
 *
 **/
data class MoveResponseModel(
        val move: BaseResponseModel?,
        val version_group_details: List<VersionGroupModel>?
)

data class VersionGroupModel(
        val level_learned_at: Int?,
        val move_learn_method: BaseResponseModel?,
        val version_group: BaseResponseModel?
)

