package com.faris.pokedex_clone.viewModel

import android.app.Application
import com.faris.pokedex_clone.base.BaseViewModel
import com.faris.pokedex_clone.localDb.FavouriteDatabase
import com.faris.pokedex_clone.repository.FavouriteRepository

/**
 * Created by farisjakpau on 07/03/2021.
 * Pokedex_Clone.
 */

class FavouriteActivityViewModel(application: Application) : BaseViewModel(application) {
    private val favouriteRepo = FavouriteRepository(FavouriteDatabase(application))

    fun getFavouritePokemon() = favouriteRepo.getAllFavourite()

}