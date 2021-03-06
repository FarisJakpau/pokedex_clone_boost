package com.faris.pokedex_clone.network.api

import com.faris.pokedex_clone.network.ApiClient
import com.faris.pokedex_clone.network.model.response.AbilityDetailResponseModel
import com.faris.pokedex_clone.network.model.response.PokemonListResponseModel
import com.faris.pokedex_clone.network.model.response.PokemonResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by FarisJakpau on 4/03/2021
 *
 **/
interface PokemonAPI {
    companion object {
        val instance: PokemonAPI
            get() {
                return ApiClient.client(PokemonAPI::class.java)
            }
    }

    @GET("pokemon/{pokemon_id}")
    fun getPokemon(@Path(value = "pokemon_id") pokemon_id: String?)
            : Deferred<Response<PokemonResponseModel>>

    @GET("pokemon")
    fun getPokemonList(
        @Query("limit") limit: String?,
        @Query("offset") offset: String?
    ): Deferred<Response<PokemonListResponseModel>>

    @GET("ability/{ability_id}/")
    fun getPokemonAbility(@Path(value = "ability_id") ability_id: String?)
            : Deferred<Response<AbilityDetailResponseModel>>
}