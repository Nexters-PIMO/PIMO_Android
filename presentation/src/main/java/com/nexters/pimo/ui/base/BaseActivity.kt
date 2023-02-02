package com.nexters.pimo.ui.base

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity

abstract class BaseActivity : ComponentActivity() {

    protected inline val TAG get() = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected open fun handleException(throwable: Throwable) {
        Log.e(TAG, throwable.stackTraceToString())
        finish()
    }

    override fun finish() {
        super.finish()
    }
}
