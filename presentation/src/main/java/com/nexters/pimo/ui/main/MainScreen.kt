package com.nexters.pimo.ui.main

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nexters.pimo.ui.R
import com.nexters.pimo.ui.component.FimoBottomBar
import com.nexters.pimo.ui.component.FimoBottomSheetContent
import com.nexters.pimo.ui.component.NoRippleInteractionSource
import com.nexters.pimo.ui.component.bottomPanelHeight
import com.nexters.pimo.ui.feed.FeedScreen
import com.nexters.pimo.ui.home.HomeScreen
import com.nexters.pimo.ui.theme.FimoTheme
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    startUploadActivity: () -> Unit,
    startFriendActivity: () -> Unit,
    startSettingsActivity: () -> Unit,
) {
    val state = viewModel.collectAsState().value

    val navController = rememberNavController()
    val navigator = rememberNavigator(navController = navController)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    var isSelectedPostIsMine by remember { mutableStateOf(false) }

    val bottomSheetTextStyle = FimoTheme.typography.regular.copy(
        fontSize = 18.sp,
        color = FimoTheme.colors.black
    )

    BackHandler(enabled = modalBottomSheetState.isVisible) {
        coroutineScope.launch { modalBottomSheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            FimoBottomSheetContent {
                if (isSelectedPostIsMine) {
                    TextButton(
                        onClick = {},
                        interactionSource = NoRippleInteractionSource
                    ) {
                        Text(
                            text = stringResource(id = R.string.edit),
                            style = bottomSheetTextStyle
                        )
                    }
                    TextButton(
                        onClick = {},
                        interactionSource = NoRippleInteractionSource
                    ) {
                        Text(
                            text = stringResource(id = R.string.delete),
                            style = bottomSheetTextStyle
                        )
                    }
                } else {
                    TextButton(
                        onClick = {},
                        interactionSource = NoRippleInteractionSource
                    ) {
                        Text(
                            text = stringResource(id = R.string.report),
                            style = bottomSheetTextStyle
                        )
                    }
                }
            }
        }
    ) {
        Scaffold(
            bottomBar = {
                FimoBottomBar(
                    currentDestination = currentDestination,
                    onNavigate = { navigator.navigateTo(it) },
                    onActionClick = startUploadActivity,
                    profileImageUrl = "https://avatars.githubusercontent.com/u/72238126?v=4"
                )
            },
        ) {
            NavHost(
                navController = navController,
                startDestination = Destination.Home.route,
                modifier = Modifier.padding(bottom = bottomPanelHeight)
            ) {
                composable(Destination.Home.route) {
                    HomeScreen(
                        onClickMore = {
                            viewModel.onSelectPost(it)
                            coroutineScope.launch { modalBottomSheetState.show() }
                        },
                        startSettingsActivity = startSettingsActivity
                    )
                }
                composable(Destination.Feed.route) {
                    FeedScreen(
                        onClickMore = {
                            viewModel.onSelectPost(it)
                            coroutineScope.launch { modalBottomSheetState.show() }
                        },
                        startFriendActivity = startFriendActivity
                    )
                }
            }
        }
    }
}
