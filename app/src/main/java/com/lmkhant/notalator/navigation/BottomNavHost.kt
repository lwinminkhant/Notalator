package com.lmkhant.notalator.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lmkhant.notalator.ui.favourite.FavouriteScreen
import com.lmkhant.notalator.ui.home.HomeScreen
import com.lmkhant.notalator.ui.library.LibraryScreen

@Composable
fun BottomNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreens.Home.route,
        modifier = modifier
    ) {
        composable(route = BottomBarScreens.Home.route) {
            HomeScreen()
        }

        composable(route = BottomBarScreens.Add.route) {
            FavouriteScreen()
        }

        composable(route = BottomBarScreens.Library.route) {
            LibraryScreen()
        }
    }
}