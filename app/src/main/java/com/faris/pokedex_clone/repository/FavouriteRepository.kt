package com.faris.pokedex_clone.repository

import androidx.lifecycle.LiveData
import com.faris.pokedex_clone.localDb.FavouriteDatabase
import com.faris.pokedex_clone.localDb.model.FavouriteModel

/**
 * Created by farisjakpau on 07/03/2021.
 * Pokedex_Clone.
 */

class FavouriteRepository(private val favouriteDatabase: FavouriteDatabase) {

    suspend fun insertFavourite(favouriteModel: FavouriteModel) =
        favouriteDatabase.getFavouriteDao().insert(favouriteModel)

    suspend fun deleteFavourite(favouriteModel: FavouriteModel) =
        favouriteDatabase.getFavouriteDao().delete(favouriteModel)

    fun getAllFavourite(): LiveData<List<FavouriteModel>> =
        favouriteDatabase.getFavouriteDao().getAllFavourite()
}