package com.faris.pokedex_clone.repository

import com.faris.pokedex_clone.base.BaseRepository
import com.faris.pokedex_clone.network.ApiResultHandler
import com.faris.pokedex_clone.network.api.PokemonAPI
import com.faris.pokedex_clone.network.model.response.AbilityDetailResponseModel
import com.faris.pokedex_clone.network.model.response.PokemonListResponseModel
import com.faris.pokedex_clone.network.model.response.PokemonResponseModel

/**
 * Created by FarisJakpau on 4/03/2021
 *
 **/
class PokemonRepository(private val api: PokemonAPI) : BaseRepository() {

    suspend fun getPokemon(pokemonId: String?): ApiResultHandler<PokemonResponseModel> {
        return safeApiCall(
                call = { api.getPokemon(pokemonId).await() },
            errorMessage = "Error Get Pokemon"
        )
    }

    suspend fun getPokemonList(
        limit: String,
        offset: String
    ): ApiResultHandler<PokemonListResponseModel> {
        return safeApiCall(
            call = { api.getPokemonList(limit, offset).await() },
            errorMessage = "Error Get Pokemon List"
        )
    }

    suspend fun getPokemonAbility(abilityId: String?): ApiResultHandler<AbilityDetailResponseModel> {
        return safeApiCall(
            call = { api.getPokemonAbility(abilityId).await() },
            errorMessage = "Error Get Pokemon Ability"
        )
    }
}