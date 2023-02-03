package com.nexters.pimo.ui.base

import android.app.Activity
import androidx.annotation.AnimRes
import com.nexters.pimo.ui.R

object ActivityTransition {

    enum class Animation(
        @AnimRes val enterAnimRes: Int,
        @AnimRes val exitAnimRes: Int,
        @AnimRes val popEnterAnimRes: Int,
        @AnimRes val popExitAnimRes: Int,
    ) {
        Cover(
            enterAnimRes = R.anim.cover_enter,
            exitAnimRes = R.anim.cover_exit,
            popEnterAnimRes = R.anim.cover_pop_enter,
            popExitAnimRes = R.anim.cover_pop_exit
        ),
        Push(
            enterAnimRes = R.anim.push_enter,
            exitAnimRes = R.anim.push_exit,
            popEnterAnimRes = R.anim.push_pop_enter,
            popExitAnimRes = R.anim.push_pop_exit
        );
    }

    fun Activity.overridePendingTransition(animation: Animation) =
        overridePendingTransition(animation.enterAnimRes, animation.exitAnimRes)

    fun Activity.overridePendingPopTransition(animation: Animation) =
        overridePendingTransition(animation.popEnterAnimRes, animation.popExitAnimRes)
}
