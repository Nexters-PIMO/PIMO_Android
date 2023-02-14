package com.nexters.pimo.ui.main

sealed class Destination(
    val route: String
) {
    object Home : Destination(ROUTE_HOME)
    object Feed : Destination(ROUTE_FEED)
    object Setting : Destination(ROUTE_SETTING)

    companion object {
        private const val ROUTE_HOME = "home"
        private const val ROUTE_FEED = "feed"
        private const val ROUTE_SETTING = "setting"
    }
}
