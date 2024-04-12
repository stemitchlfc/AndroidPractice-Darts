package com.example.dartsapplication.screens.practice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dartsapplication.entities.PracticeEntity
import com.example.dartsapplication.screens.practice.viewmodel.PracticeViewModel
import com.example.dartsapplication.ui.theme.ColorDarkGrey
import com.example.dartsapplication.ui.theme.ColorLighterGreen

@Composable
fun PracticeSetupPage(
    practiceViewModel: PracticeViewModel,
    navigateToStatsScreen: () -> Unit,
    navigateToPracticeScreen: () -> Unit
){


    Column(
        modifier = Modifier.fillMaxWidth()
            .fillMaxHeight()
            .background(ColorLighterGreen)
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val name by practiceViewModel.playerName.collectAsStateWithLifecycle()
        val startingScore by practiceViewModel.startingScore.collectAsStateWithLifecycle()


        val onNameEntered: (value: String) -> Unit = remember {
            return@remember practiceViewModel::setPlayerName
        }
        val onStartingScoreEntered: (value: String) -> Unit = remember {
            return@remember practiceViewModel::setStartingScore
        }

        val onSubmit: (value: PracticeEntity) -> Unit = remember {
            return@remember practiceViewModel::insertPractice
        }


        OutlinedTextField(
            value = name,
            onValueChange = {
                onNameEntered(it)
            },
            placeholder = {
                Text(text = "Enter the name of the player")
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(), maxLines = 1
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = startingScore, onValueChange = {
                onStartingScoreEntered(it)
            },
            placeholder = {
                Text(text = "Enter the starting score")
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(),
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            onSubmit(
                PracticeEntity(
                    name = name,
                    startingScore = startingScore,
                    remainingScore = startingScore
                )
            )
            navigateToPracticeScreen()
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = ColorDarkGrey,
                contentColor = Color.White
            )) {
            Text(text = "Create Game")
        }

        Button(onClick = { navigateToStatsScreen() },
            colors = ButtonDefaults.buttonColors(
                containerColor = ColorDarkGrey,
                contentColor = Color.White
            )) {
            Text(text = "Go to Stats")
        }

    }
}

