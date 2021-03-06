package com.faris.pokedex_clone.localDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.faris.pokedex_clone.localDb.model.FavouriteModel

/**
 * Created by farisjakpau on 07/03/2021.
 * Pokedex_Clone.
 */

@Database(
    entities = [FavouriteModel::class],
    version = 1,
    exportSchema = false
)
abstract class FavouriteDatabase : RoomDatabase() {
    abstract fun getFavouriteDao(): FavouriteDao

    companion object {
        private const val DB_NAME = "favourite_database.db"
        @Volatile
        private var instance: FavouriteDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FavouriteDatabase::class.java,
            DB_NAME
        ).build()
    }


}