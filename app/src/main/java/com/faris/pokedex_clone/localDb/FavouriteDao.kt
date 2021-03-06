package com.faris.pokedex_clone.localDb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.faris.pokedex_clone.localDb.model.FavouriteModel

/**
 * Created by farisjakpau on 06/03/2021.
 * Pokedex_Clone.
 */
@Dao
interface FavouriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favourite: FavouriteModel)

    @Update
    suspend fun update(favourite: FavouriteModel)

    @Delete
    suspend fun delete(favourite: FavouriteModel)

    @Query("SELECT * FROM favourite_table")
    fun getAllFavourite(): LiveData<List<FavouriteModel>>

    @Query("DELETE FROM favourite_table WHERE pokemon_id = :pokemon_id")
    suspend fun deletePokemonById(pokemon_id: String)
}