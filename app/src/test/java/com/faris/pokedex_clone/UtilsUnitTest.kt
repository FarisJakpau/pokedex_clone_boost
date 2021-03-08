package com.faris.pokedex_clone

import com.faris.pokedex_clone.util.getPokemonId
import org.junit.Assert
import org.junit.Test

/**
 * Created by FarisJakpau on 8/03/2021
 *
 **/
class UtilsUnitTest {
    @Test
    fun get_pokemon_id_isCorrect() {
        val testUrl = "https://pokeapi.co/api/v2/pokemon/3/"
        Assert.assertEquals("3", testUrl.getPokemonId())
    }

}