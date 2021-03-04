package com.faris.pokedex_clone.base

import android.app.Application

/**
 * Created by FarisJakpau on 4/03/2021
 *
 **/
class BaseApplication : Application() {

    companion object {
        lateinit var instance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}