package com.faris.pokedex_clone.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.faris.pokedex_clone.base.BaseViewModel
import com.faris.pokedex_clone.network.api.PokemonAPI
import com.faris.pokedex_clone.network.model.response.PokemonListResponseModel
import com.faris.pokedex_clone.repository.PokemonRepository
import kotlinx.coroutines.launch

/**
 * Created by FarisJakpau on 4/03/2021
 *
 **/
class MainActivityViewModel(application: Application) : BaseViewModel(application) {

    val pokemonListResponse: MutableLiveData<PokemonListResponseModel>? = MutableLiveData()
    private val pokemonRepo: PokemonRepository = PokemonRepository(PokemonAPI.instance)

    fun getPokemon(pokemonId: String?) {
        scope.launch(exceptionHandler) {
            val result = pokemonRepo.getPokemon(pokemonId)
            //pokemonListResponse?.postValue(result)
        }
    }

    fun getPokemonList(limit: Int) {
        scope.launch(exceptionHandler) {
            val result = pokemonRepo.getPokemonList(limit.toString())
            pokemonListResponse?.postValue(result)
        }
    }
}