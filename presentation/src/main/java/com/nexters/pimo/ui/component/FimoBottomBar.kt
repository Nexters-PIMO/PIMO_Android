package com.nexters.pimo.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import coil.compose.AsyncImage
import com.nexters.pimo.ui.R
import com.nexters.pimo.ui.main.Destination
import com.nexters.pimo.ui.theme.FimoTheme

@Composable
fun FimoBottomBar(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    onNavigate: (Destination) -> Unit,
    @DrawableRes actionIconRes: Int = R.drawable.ic_action,
    onActionClick: () -> Unit,
    profileImageUrl: String
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val itemsHorizontalPadding =
        ((screenWidth - bottomActionButtonSize) / 2 - bottomBarItemIconSize) / 2 - 4.dp

    @Composable
    fun Home() = FimoBottomBarItem(
        destination = Destination.Home,
        currentDestination = currentDestination,
        onSelected = {
            Icon(
                painter = painterResource(id = R.drawable.ic_home),
                contentDescription = null,
                modifier = Modifier.size(bottomBarItemIconSize),
                tint = FimoTheme.colors.black
            )
        },
        onNotSelected = {
            Icon(
                painter = painterResource(id = R.drawable.ic_home_outlined),
                contentDescription = null,
                modifier = Modifier.size(bottomBarItemIconSize),
                tint = FimoTheme.colors.black
            )
        },
        onClick = { onNavigate(Destination.Home) }
    )

    @Composable
    fun Feed() = FimoBottomBarItem(
        destination = Destination.Feed,
        currentDestination = currentDestination,
        onSelected = {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    shape = CircleShape,
                    color = FimoTheme.colors.black,
                    modifier = Modifier.size(bottomBarItemIconSize + 5.dp)
                ) {}
                Surface(
                    shape = CircleShape,
                    color = FimoTheme.colors.white,
                    modifier = Modifier.size(bottomBarItemIconSize + 2.dp)
                ) {}
                AsyncImage(
                    model = profileImageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(bottomBarItemIconSize)
                )
            }
        },
        onNotSelected = {
            AsyncImage(
                model = profileImageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(bottomBarItemIconSize)
                    .clip(CircleShape)
            )
        },
        onClick = { onNavigate(Destination.Feed) }
    )

    Box(
        modifier = modifier
            .height(102.dp)
            .fillMaxWidth()
    ) {
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(bottomPanelHeight)
                .fillMaxWidth(),
            color = FimoTheme.colors.greyEF,
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .padding(horizontal = itemsHorizontalPadding)
            ) {
                Home()
                Feed()
            }
        }
        FilledIconButton(
            onClick = onActionClick,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(bottomActionButtonSize),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = FimoTheme.colors.secondary,
                contentColor = FimoTheme.colors.primary
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_action),
                contentDescription = null,
                modifier = Modifier.size(42.dp),
                tint = FimoTheme.colors.primary
            )
        }
    }
}

@Composable
fun FimoBottomBarItem(
    destination: Destination,
    currentDestination: NavDestination?,
    onSelected: @Composable () -> Unit,
    onNotSelected: @Composable () -> Unit,
    onClick: () -> Unit,
) {
    val selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true

    IconButton(onClick = onClick) {
        if (selected) onSelected() else onNotSelected()
    }
}

val bottomPanelHeight = 72.dp
val bottomActionButtonSize = 72.dp
val bottomBarItemIconSize = 28.dp
