package com.faris.pokedex_clone.network

/**
 * Created by FarisJakpau on 4/03/2021
 *
 **/
sealed class ApiResultHandler<out T : Any> {
    data class Success<out T : Any>(val data: T) : ApiResultHandler<T>()
    data class Error(val exception: Exception) : ApiResultHandler<Nothing>()
}