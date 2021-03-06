package com.faris.pokedex_clone.enum

/**
 * Created by farisjakpau on 06/03/2021.
 * Pokedex_Clone.
 */

enum class PokemonTypeEnum (
    val type:String,
    val colorCode: String
) {
    NORMAL("normal" ,"#A8A77A"),
    FIGHTING("fighting", "#C22E28"),
    FLYING("flying","#A98FF3"),
    POISON("poison","#A33EA1"),
    GROUND("ground","#E2BF65"),
    ROCK("rock","#B6A136"),
    BUG("bug","#A6B91A"),
    GHOST("ghost","#735797"),
    STEEL("steel","#B7B7CE"),
    FIRE("fire","#EE8130"),
    WATER("water","#6390F0"),
    GRASS("grass","#7AC74C"),
    ELECTRIC("electric","#F7D02C"),
    PSYCHIC("psychic","#F95587"),
    ICE("ice","#96D9D6"),
    DRAGON("dragon","#6F35FC"),
    DARK("dark","#705746"),
    FAIRY("fairy","#D685AD"),
    UNKNOWN("unknown","#FFFFFF"),
    SHADOW("shadow","##95a5a6")

}