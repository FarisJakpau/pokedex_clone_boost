package com.faris.pokedex_clone.util

/**
 * Created by farisjakpau on 06/03/2021.
 * Pokedex_Clone.
 */

class EventHolder<T> {
    val onClick: SingleLiveEvent<T> = SingleLiveEvent()
}