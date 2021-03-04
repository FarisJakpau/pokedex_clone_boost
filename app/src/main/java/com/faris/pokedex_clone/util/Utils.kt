package com.faris.pokedex_clone.util

import android.util.Log

/**
 * Created by FarisJakpau on 5/03/2021
 *
 **/
fun String?.getPokemonId(): String {
    val splitText = this?.trim()?.split("/")
    Log.e("asdasd", "${splitText?.get(splitText.size - 1)}")
    return splitText?.get(6).toString()
}
