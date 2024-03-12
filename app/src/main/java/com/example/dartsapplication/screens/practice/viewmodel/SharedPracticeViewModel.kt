package com.example.dartsapplication.screens.practice.viewmodel


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedPracticeViewModel(
    navController: NavHostController
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this){
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}