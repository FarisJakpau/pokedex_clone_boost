package com.faris.pokedex_clone.base

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Created by FarisJakpau on 4/03/2021
 *
 **/
abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private val argument: Bundle = Bundle()

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    protected val scope = CoroutineScope(coroutineContext)

    protected val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("Error", "${throwable.message}")
    }

    fun addBundle(bundle: Bundle) {
        argument.putAll(bundle)
    }

}