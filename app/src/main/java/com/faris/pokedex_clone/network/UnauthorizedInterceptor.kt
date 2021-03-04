package com.faris.pokedex_clone.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by FarisJakpau on 4/03/2021
 *
 **/
class UnauthorizedInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code == 401) {
            Log.e("UnauthorizedInterceptor", "${response.message}")
        }
        return response
    }


}