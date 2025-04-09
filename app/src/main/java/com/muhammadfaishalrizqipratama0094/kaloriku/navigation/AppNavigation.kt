package com.muhammadfaishalrizqipratama0094.kaloriku.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.muhammadfaishalrizqipratama0094.kaloriku.ui.screens.HomeScreen
import com.muhammadfaishalrizqipratama0094.kaloriku.ui.screens.ResultScreen

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Result : Screen("result/{calories}") {
        fun createRoute(calories: Int): String {
            return "result/$calories"
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Result.route,
            arguments = listOf(
                navArgument("calories") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val calories = backStackEntry.arguments?.getInt("calories") ?: 0
            ResultScreen(
                calories = calories,
                navController = navController
            )
        }
    }
}