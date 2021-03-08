package com.faris.pokedex_clone.network.model.response

/**
 * Created by FarisJakpau on 4/03/2021
 *
 **/
data class PokemonResponseModel(
        val abilities: List<AbilityResponseModel>?,
        val forms: List<BaseResponseModel>?,
        val game_indices: List<GameIndicesResponseModel>?,
        val height: Int?,
        val held_items: List<Nothing>,
        val id: Int?,
        val is_default: Boolean?,
        val location_area_encounters: String?,
        val moves: List<MoveResponseModel>?,
        val name: String?,
        val order: Int?,
        val past_types: List<Nothing>?,
        val species: BaseResponseModel?,
        val stats: List<StatResponseModel>?,
        val types: List<TypeResponseModel>?,
        val weight: Int?
)
