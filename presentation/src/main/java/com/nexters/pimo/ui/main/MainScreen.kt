package com.nexters.pimo.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nexters.pimo.ui.component.FimoBottomBar
import com.nexters.pimo.ui.component.bottomPanelHeight
import com.nexters.pimo.ui.feed.FeedScreen
import com.nexters.pimo.ui.home.HomeScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navigator = rememberNavigator(navController = navController)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            FimoBottomBar(
                currentDestination = currentDestination,
                onNavigate = { navigator.navigateTo(it) },
                onActionClick = { /*TODO*/ },
                profileImageUrl = "https://avatars.githubusercontent.com/u/72238126?v=4"
            )
        },
    ) {
        NavHost(
            navController = navController,
            startDestination = Destination.Home.route,
            modifier = Modifier.padding(bottom = bottomPanelHeight)
        ) {
            composable(Destination.Home.route) { HomeScreen() }
            composable(Destination.Feed.route) { FeedScreen() }
        }
    }
}