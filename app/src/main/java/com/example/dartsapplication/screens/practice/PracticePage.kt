package com.example.dartsapplication.screens.practice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dartsapplication.screens.practice.viewmodel.PracticeViewModel
import com.example.dartsapplication.ui.GameButtons
import com.example.dartsapplication.ui.GameCheckoutView
import com.example.dartsapplication.ui.GameDataView

@Composable
fun PracticePage(
    practiceViewModel: PracticeViewModel,
    navigateToStatsScreen: () -> Unit
){

    LaunchedEffect(key1 = true, block = {
        practiceViewModel.getPractice()
    })

    val columnSpacing: Dp = 6.dp

    val practiceState by practiceViewModel.practiceGame.collectAsStateWithLifecycle()
    val pageState = practiceViewModel.practiceState


    Box(modifier = Modifier
        .background(Color.Black)
        .fillMaxSize()){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(columnSpacing)
        ) {
            Row {
                GameDataView(
                    data = practiceState.remainingScore,
                    dataType = "${practiceState.name}, your Remaining Score is:",
                    modifier = Modifier
                )
            }
            Row {
                GameDataView(
                    data = practiceState.average,
                    dataType = "Average Score",
                    modifier = Modifier
                        .weight(2f)
                        .aspectRatio(2f)
                )
            }
            Row{
                GameDataView(
                    data = practiceState.previousScore,
                    dataType = "Previous Score",
                    modifier = Modifier
            )
            }
            Row {
                GameDataView(
                    data = pageState.enteredScore,
                    dataType = "",
                    modifier = Modifier
                )
            }
            Row {
                GameCheckoutView(
                    state = practiceState,
                    onAction = practiceViewModel::onAction,
                    navigateToStatsScreen = {navigateToStatsScreen()}
                )
            }

            Row {
                GameButtons(
                    onAction = practiceViewModel::onAction
                )
            }
        }

    }
}


