package com.faris.pokedex_clone.localDb.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by farisjakpau on 06/03/2021.
 * Pokedex_Clone.
 */

@Entity(tableName = "favourite_table")
data class FavouriteModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val pokemon_id: String?
)