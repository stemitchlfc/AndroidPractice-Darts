package com.example.dartsapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dartsapplication.navigation.Destinations.HOME_SCREEN
import com.example.dartsapplication.navigation.Destinations.PRACTICE_SCREEN
import com.example.dartsapplication.navigation.Destinations.PRACTICE_SETUP_SCREEN
import com.example.dartsapplication.navigation.Destinations.PRACTICE_STATISTICS_SCREEN

object Destinations {
    const val HOME_SCREEN = "home"
    const val PRACTICE_SCREEN = "practice"
    const val PRACTICE_SETUP_SCREEN = "practice_setup"
    const val PRACTICE_STATISTICS_SCREEN = "practice_statistics"
}

@Composable
fun rememberAppNavController(
    navController: NavHostController = rememberNavController()
): AppNavController = remember(navController) {
    AppNavController(navController)
}

@Stable
class AppNavController(
    val navController: NavHostController
) {
    fun navigateToHomePage() {
        navController.navigate(HOME_SCREEN)
    }

    fun navigateToPracticePage() {
        navController.navigate(PRACTICE_SCREEN)
    }

    fun navigateToPracticeSetupPage(){
        navController.navigate(PRACTICE_SETUP_SCREEN)
    }

    fun navigateToPracticeStatsPage(){
        navController.navigate(PRACTICE_STATISTICS_SCREEN)
    }
}