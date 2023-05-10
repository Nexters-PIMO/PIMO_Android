package com.nexters.pimo.ui.main

sealed class


Destination(
    val route: String
) {
    object Home : Destination(ROUTE_HOME)
    object Feed : Destination(ROUTE_FEED)

    companion object {
        private const val ROUTE_HOME = "home"
        private const val ROUTE_FEED = "feed"
    }
}
