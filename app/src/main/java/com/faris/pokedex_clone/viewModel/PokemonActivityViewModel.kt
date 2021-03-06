package com.faris.pokedex_clone.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.faris.pokedex_clone.base.BaseViewModel
import com.faris.pokedex_clone.localDb.FavouriteDatabase
import com.faris.pokedex_clone.localDb.model.FavouriteModel
import com.faris.pokedex_clone.network.ApiResultHandler
import com.faris.pokedex_clone.network.api.PokemonAPI
import com.faris.pokedex_clone.network.model.response.AbilityDetailResponseModel
import com.faris.pokedex_clone.network.model.response.PokemonResponseModel
import com.faris.pokedex_clone.repository.FavouriteRepository
import com.faris.pokedex_clone.repository.PokemonRepository
import kotlinx.coroutines.launch

/**
 * Created by farisjakpau on 06/03/2021.
 * Pokedex_Clone.
 */

class PokemonActivityViewModel (application:Application) : BaseViewModel(application) {
    private val pokemonRepo = PokemonRepository(PokemonAPI.instance)
    private val favouriteRepo = FavouriteRepository(FavouriteDatabase(application))

    val pokemonResponse: MutableLiveData<PokemonResponseModel> = MutableLiveData()
    val pokemonAbilityResponse: MutableLiveData<AbilityDetailResponseModel> = MutableLiveData()

    fun getFavouritePokemon() = favouriteRepo.getAllFavourite()

    suspend fun insertFavourite(favouriteModel: FavouriteModel) =
        favouriteRepo.insertFavourite(favouriteModel)

    suspend fun deleteFavourite(favouriteModel: FavouriteModel) =
        favouriteRepo.deleteFavourite(favouriteModel)

    fun getPokemon(pokemonId: String) {
        scope.launch(exceptionHandler) {
            when (val result = pokemonRepo.getPokemon(pokemonId)) {
                is ApiResultHandler.Success -> {
                    pokemonResponse.postValue(result.data)
                }

                is ApiResultHandler.Error -> {

                }
            }
        }
    }

    fun getPokemonAbility(abilityId: String?) {
        scope.launch(exceptionHandler) {
            when (val result = pokemonRepo.getPokemonAbility(abilityId)) {
                is ApiResultHandler.Success -> {
                    pokemonAbilityResponse.postValue(result.data)
                }
                is ApiResultHandler.Error -> {

                }
            }
        }
    }
}