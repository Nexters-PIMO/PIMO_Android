package com.nexters.pimo.ui.base

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.nexters.pimo.ui.base.ActivityTransition.overridePendingPopTransition
import com.nexters.pimo.ui.base.ActivityTransition.overridePendingTransition

abstract class BaseActivity : ComponentActivity() {

    protected inline val TAG get() = this::class.java.simpleName
    protected open val transitionAnimation = ActivityTransition.Animation.Push

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(transitionAnimation)
    }

    protected open fun handleException(throwable: Throwable) {
        Log.e(TAG, throwable.stackTraceToString())
        finish()
    }

    override fun finish() {
        super.finish()
        overridePendingPopTransition(transitionAnimation)
    }
}
