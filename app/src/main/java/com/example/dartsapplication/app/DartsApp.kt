package com.example.dartsapplication.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.dartsapplication.navigation.Destinations.BOBS_ROUTE
import com.example.dartsapplication.navigation.Destinations.BOBS_SCREEN
import com.example.dartsapplication.navigation.Destinations.HOME_SCREEN
import com.example.dartsapplication.navigation.Destinations.PRACTICE_ROUTE
import com.example.dartsapplication.navigation.Destinations.PRACTICE_SCREEN
import com.example.dartsapplication.navigation.Destinations.PRACTICE_SETUP_SCREEN
import com.example.dartsapplication.navigation.Destinations.PRACTICE_STATISTICS_SCREEN
import com.example.dartsapplication.screens.bobsts.BobsTSPage
import com.example.dartsapplication.screens.bobsts.viewmodel.BobsViewModel
import com.example.dartsapplication.screens.bobsts.viewmodel.sharedBobsViewModel
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
                    },
                    navigateToBobsPage = {
                        appNavController.navigate(BOBS_SCREEN)
                    }
                )
            }
            navigation(
                startDestination = PRACTICE_SETUP_SCREEN,
                route = PRACTICE_ROUTE
            ){
                composable(PRACTICE_SETUP_SCREEN){ entry ->
                    val practiceViewModel = entry.sharedPracticeViewModel<PracticeViewModel>(appNavController)

                    PracticeSetupPage(
                        practiceViewModel,
                        navigateToStatsScreen = {
                            appNavController.navigate(PRACTICE_STATISTICS_SCREEN)
                        },
                        navigateToPracticeScreen = {
                            appNavController.navigate(PRACTICE_SCREEN)
                        }
                    ) 
                }
                composable(PRACTICE_STATISTICS_SCREEN){ entry ->
                    val practiceViewModel = entry.sharedPracticeViewModel<PracticeViewModel>(appNavController)
                    PracticeStatistics(
                        practiceViewModel = practiceViewModel
                    )
                }
                composable(PRACTICE_SCREEN){ entry ->
                    val practiceViewModel = entry.sharedPracticeViewModel<PracticeViewModel>(appNavController)

                    PracticePage(
                        practiceViewModel = practiceViewModel,
                        navigateToStatsScreen = {
                            appNavController.navigate(PRACTICE_STATISTICS_SCREEN)
                        }
                    )
                }
            }
            navigation(
                startDestination = BOBS_SCREEN,
                route = BOBS_ROUTE
            ){
                composable(BOBS_SCREEN){entry ->
                    val bobsViewModel = entry.sharedBobsViewModel<BobsViewModel>(appNavController)
                    BobsTSPage(
                        bobsViewModel
                    )
                }
            }
        }
    }
}
