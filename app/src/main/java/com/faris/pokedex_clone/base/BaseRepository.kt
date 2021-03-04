package com.faris.pokedex_clone.base

import android.util.Log
import com.faris.pokedex_clone.network.ApiResultHandler
import retrofit2.Response

/**
 * Created by FarisJakpau on 4/03/2021
 *
 **/
open class BaseRepository {

    private val TAG = BaseRepository::class.java.simpleName

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val apiResultHandler: ApiResultHandler<T> = safeApiResult(call, errorMessage)
        var data: T? = null

        when (apiResultHandler) {
            is ApiResultHandler.Success -> {
                data = apiResultHandler.data
            }

            is ApiResultHandler.Error -> {
                Log.e(TAG, "${apiResultHandler.exception}")
            }
        }

        return data
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