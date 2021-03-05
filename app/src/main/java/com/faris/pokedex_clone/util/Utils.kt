package com.faris.pokedex_clone.util

/**
 * Created by FarisJakpau on 5/03/2021
 *
 **/

/**
 * getPokemonId return the pokemon id from pokemon link
 * ex: https://pokeapi.co/api/v2/pokemon/3/
 * split '/' from the url
 * will return 3
 */
fun String?.getPokemonId(): String {
    val splitText = this?.trim()?.split("/")
    return splitText?.get(splitText.size - 2).toString()
}

/**
 * get Pokemon image from the different endpoint
 */
fun getImageLink(pokemonId: String): String {
    return Const.BASE_API_IMAGE_URL + pokemonId + ".png"
}
