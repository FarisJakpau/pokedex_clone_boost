package com.faris.pokedex_clone.network

import com.faris.pokedex_clone.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by FarisJakpau on 4/03/2021
 *
 **/
interface ApiClient {

    companion object Client {
        fun <T> client(serviceClass: Class<T>): T {
            return build().create(serviceClass)
        }

        private fun build(): Retrofit {
            val logging = HttpLoggingInterceptor()
            logging.level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }

            val okBuilder = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .addInterceptor(UnauthorizedInterceptor())
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)

            val client = okBuilder.build()
            return Retrofit.Builder()
                    .baseUrl("https://pokeapi.co/api/v2/")
                    .client(client)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()

        }
    }
}