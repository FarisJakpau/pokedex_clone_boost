package com.faris.pokedex_clone.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.faris.pokedex_clone.base.BaseViewModel
import com.faris.pokedex_clone.network.ApiResultHandler
import com.faris.pokedex_clone.network.api.PokemonAPI
import com.faris.pokedex_clone.network.model.response.PokemonListResponseModel
import com.faris.pokedex_clone.repository.PokemonRepository
import kotlinx.coroutines.launch

/**
 * Created by FarisJakpau on 4/03/2021
 *
 **/
class MainActivityViewModel(application: Application) : BaseViewModel(application) {

    val pokemonListResponse: MutableLiveData<PokemonListResponseModel> = MutableLiveData()
    private val pokemonRepo: PokemonRepository = PokemonRepository(PokemonAPI.instance)

    fun getPokemonList(limit: Int, offset: Int) {
        scope.launch(exceptionHandler) {
            when (val result = pokemonRepo.getPokemonList(limit.toString(), offset.toString())) {
                is ApiResultHandler.Success -> {
                    pokemonListResponse.postValue(result.data)
                }
                is ApiResultHandler.Error -> {
                    errorResponse.postValue(result.exception.message)
                }
            }
        }
    }
}