package com.faris.pokedex_clone.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.faris.pokedex_clone.base.BaseViewModel
import com.faris.pokedex_clone.network.ApiResultHandler
import com.faris.pokedex_clone.network.api.PokemonAPI
import com.faris.pokedex_clone.network.model.response.PokemonResponseModel
import com.faris.pokedex_clone.repository.PokemonRepository
import kotlinx.coroutines.launch

/**
 * Created by farisjakpau on 06/03/2021.
 * Pokedex_Clone.
 */

class PokemonActivityViewModel (application:Application) : BaseViewModel(application) {
    private val pokemonRepo = PokemonRepository(PokemonAPI.instance)
    val pokemonResponse: MutableLiveData<PokemonResponseModel> = MutableLiveData()

    fun getPokemon(pokemonId: String) {
        scope.launch(exceptionHandler) {
            when(val result = pokemonRepo.getPokemon(pokemonId)) {
                is ApiResultHandler.Success -> {
                    pokemonResponse.postValue(result.data)
                }

                is ApiResultHandler.Error -> {

                }
            }
        }
    }
}