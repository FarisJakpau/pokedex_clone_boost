package com.faris.pokedex_clone.repository

import com.faris.pokedex_clone.base.BaseRepository
import com.faris.pokedex_clone.network.api.PokemonAPI
import com.faris.pokedex_clone.network.model.response.PokemonResponseModel

/**
 * Created by FarisJakpau on 4/03/2021
 *
 **/
class PokemonRepository(private val api: PokemonAPI) : BaseRepository() {

    suspend fun getPokemon(pokemonId: String?): PokemonResponseModel? {
        return safeApiCall(
                call = { api.getPokemon(pokemonId).await() },
                errorMessage = "error"
        )
    }
}