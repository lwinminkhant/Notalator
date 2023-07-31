package com.lmkhant.notalator.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreens(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    object Home: BottomBarScreens(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Add: BottomBarScreens(
        route = "add",
        title = "Favourite",
        icon = Icons.Default.Favorite
    )
    object Library: BottomBarScreens(
        route = "library",
        title = "Library",
        icon = Icons.Default.LibraryBooks
    )
}