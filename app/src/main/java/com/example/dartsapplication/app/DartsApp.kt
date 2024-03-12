package com.example.dartsapplication.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.dartsapplication.navigation.Destinations.HOME_SCREEN
import com.example.dartsapplication.navigation.Destinations.PRACTICE_SETUP_SCREEN
import com.example.dartsapplication.screens.home.HomePage
import com.example.dartsapplication.screens.practice.PracticePage
import com.example.dartsapplication.screens.practice.PracticeSetupPage
import com.example.dartsapplication.screens.practice.PracticeStatistics
import com.example.dartsapplication.screens.practice.viewmodel.PracticeViewModel
import com.example.dartsapplication.screens.practice.viewmodel.sharedPracticeViewModel
import com.example.dartsapplication.ui.theme.DartsApplicationTheme

@Composable
fun DartsApp() {
    DartsApplicationTheme {
        val appNavController = rememberNavController()


        NavHost(
            navController = appNavController,
            startDestination = HOME_SCREEN
        ) {
            composable(HOME_SCREEN){
                HomePage(
                    navigateToPracticeSetupScreen = {
                        appNavController.navigate(PRACTICE_SETUP_SCREEN)
                    }
                )
            }
            navigation(
                startDestination = PRACTICE_SETUP_SCREEN,
                route = "practice"
            ){
                composable(PRACTICE_SETUP_SCREEN){ entry ->
                    val practiceViewModel = entry.sharedPracticeViewModel<PracticeViewModel>(appNavController)
                    PracticeSetupPage(
                        practiceViewModel,
                        navigateToStatsScreen = {
                            appNavController.navigate("stats")
                        },
                        navigateToPracticeScreen = {
                            appNavController.navigate("practice_game")
                        }
                    ) 
                }
                composable("stats"){ entry ->
                    val practiceViewModel = entry.sharedPracticeViewModel<PracticeViewModel>(appNavController)
                    PracticeStatistics(
                        practiceViewModel = practiceViewModel
                    )
                }
                composable("practice_game"){ entry ->
                    val practiceViewModel = entry.sharedPracticeViewModel<PracticeViewModel>(appNavController)
                    PracticePage(
                        practiceViewModel = practiceViewModel
                    )
                }
            }
            //dartsNavGraph(appNavController)
        }
    }
}

//private fun NavGraphBuilder.dartsNavGraph(
//    navController: AppNavController
//){
//    composable(
//        route = HOME_SCREEN
//    ) {
//        HomePage(
//            navController,
//            navigateToPracticeScreen = {navController.navigateToPracticePage()},
//            navigateToPracticeSetupScreen = {navController.navigateToPracticeSetupPage()}
//        )
//    }
//    composable(
//        route = PRACTICE_SCREEN
//    ) {
//        PracticePage()
//    }
//    composable(
//        route = PRACTICE_SETUP_SCREEN
//    ) {
//        PracticeSetupPage(
//            navController,
//            navigateToPracticeScreen = {navController.navigateToPracticePage()}
//        ) { navController.navigateToPracticeStatsPage() }
//    }
//    composable(
//        route = PRACTICE_STATISTICS_SCREEN
//    ) {
//        PracticeStatistics(
//
//        )
//    }
//}