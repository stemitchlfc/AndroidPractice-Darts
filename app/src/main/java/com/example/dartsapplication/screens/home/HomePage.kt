package com.example.dartsapplication.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dartsapplication.ui.theme.ColorDarkGrey
import com.example.dartsapplication.ui.theme.ColorLighterGreen

@Composable
fun HomePage(
    navigateToPracticeSetupScreen: () -> Unit
) {
    val padding = 8.dp
    Column(
        modifier = Modifier.fillMaxWidth()
            .fillMaxHeight()
            .background(ColorLighterGreen)
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .width(IntrinsicSize.Max)
        )
        {
            Text(
                text = "Darts Application",
                textAlign = TextAlign.Center,
                fontSize = 50.sp,
                maxLines = 2
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max)
                .background(ColorLighterGreen)
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Button(
                    onClick = { navigateToPracticeSetupScreen() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ColorDarkGrey,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Practice")
                }

            }
            Row {
                Button(
                    onClick = { navigateToPracticeSetupScreen() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ColorDarkGrey,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Practice")
                }

            }
            Row {
                Button(
                    onClick = { navigateToPracticeSetupScreen() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ColorDarkGrey,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Practice")
                }

            }
        }
    }
}
//@Preview
//@Composable
//fun DisplayHomePage(){
//    val appNavController = rememberAppNavController()
//    HomePage(appNavController,
//        navigateToPracticeScreen = {appNavController.navigateToPracticePage()},
//        navigateToPracticeSetupScreen = {appNavController.navigateToPracticeSetupPage()})
//}