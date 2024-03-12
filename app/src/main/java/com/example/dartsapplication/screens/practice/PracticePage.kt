package com.example.dartsapplication.screens.practice

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dartsapplication.entities.PracticeEntity
import com.example.dartsapplication.screens.practice.viewmodel.PracticeViewModel
import com.example.dartsapplication.ui.GameButtons
import com.example.dartsapplication.ui.GameDataView

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PracticePage(
    practiceViewModel: PracticeViewModel
){

    LaunchedEffect(key1 = true, block = {
        // we will get the student details when ever the screen is created
// Launched effect is a side effect
        practiceViewModel.getPractice(1)
    })

//    CoroutineScope(Main).launch {
//        var game = practiceViewModel.getPractice(1)
//    }

    val columnSpacing: Dp = 6.dp

    //val practiceState by practiceViewModel.practiceState.collectAsStateWithLifecycle()
   // val practiceGame = ImutablePractice(practiceState)
    val game by practiceViewModel.practice.collectAsStateWithLifecycle()
    val state = practiceViewModel.practiceState




    //val game = ImutablePractice(contents.last())





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
                    data = state.remainingScore,
                    dataType = "${game.name}, your Remaining Score is:",
                    modifier = Modifier
                        .height(500.dp)
                )
            }
            Row {
                GameDataView(
                    data = game.average,
                    dataType = "Average Score",
                    modifier = Modifier
                        .weight(2f)
                        .aspectRatio(2f)
                )
            }
            Row{
                GameDataView(
                    data = state.previousScore,
                    dataType = "Previous Score",
                    modifier = Modifier
            )
            }
            Row {
                GameDataView(
                    data = state.enteredScore,
                    dataType = "",
                    modifier = Modifier
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                GameButtons(
                    modifier = Modifier,
                    onAction = practiceViewModel::onAction
                )
            }
        }

    }
}
//
//@Preview
//@Composable
//fun DisplayGamePage(){
//    DartsApplicationTheme {
//        PracticePage()
//    }
//}

@Immutable
data class ImutablePractice(val practiceEntity: PracticeEntity)