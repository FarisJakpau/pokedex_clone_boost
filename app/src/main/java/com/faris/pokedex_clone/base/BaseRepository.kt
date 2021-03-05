package com.faris.pokedex_clone.base

import com.faris.pokedex_clone.network.ApiResultHandler
import retrofit2.Response

/**
 * Created by FarisJakpau on 4/03/2021
 *
 **/
open class BaseRepository {

    private val TAG = BaseRepository::class.java.simpleName

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): ApiResultHandler<T> {
        return safeApiResult(call, errorMessage)
    }

    private suspend fun <T : Any> safeApiResult(
            call: suspend () -> Response<T>,
            errorMessage: String
    ): ApiResultHandler<T> {

        val response = call.invoke()
        if (response.isSuccessful) return ApiResultHandler.Success(response.body()!!)

        return ApiResultHandler.Error(Exception(errorMessage))
    }
}