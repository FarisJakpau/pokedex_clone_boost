package com.faris.pokedex_clone.util

/**
 * Created by FarisJakpau on 5/03/2021
 *
 **/
fun String?.getPokemonId(): String {
    val splitText = this?.trim()?.split("/")
    //Log.e("asdasd", "${splitText?.get(splitText.size - 1)}")
    return splitText?.get(6).toString()
}

fun getImageLink(pokemonId: String): String {
    return Const.BASE_API_IMAGE_URL + pokemonId + ".png"
}
