package com.example.dartsapplication.ui

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.dartsapplication.DartsAction
import com.example.dartsapplication.entities.PracticeEntity
import com.example.dartsapplication.ui.theme.ColorDarkGreen
import com.example.dartsapplication.ui.theme.ColorLighterGreen

@Composable
fun GameCheckoutView(
    state: PracticeEntity,
    onAction: (DartsAction) -> Unit,
    navigateToStatsScreen: () -> Unit
) {
    val activity = (LocalContext.current as? Activity)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorLighterGreen)
    ) {
        if (state.isOnDouble) {
            Text(
                text = "You are on a finish, remember to finish on a double!",
                fontSize = 36.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )
        } else if (state.legComplete) {
            Column {

                Row {


                    Text(
                        text = "You have finished the leg!!! How many darts at a Double? ${state.average}, ${state.dartsThrown}, ${state.dartsAtDouble}",
                        fontSize = 36.sp,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center
                    )
                }
                Row {
                    GameButton(
                        symbol = "1",
                        modifier = Modifier
                            .background(ColorDarkGreen)
                            .aspectRatio(2f)
                            .weight(1f),
                        onClick = {
                            onAction(DartsAction.Double(1))
                            navigateToStatsScreen()
                        }
                    )
                    GameButton(
                        symbol = "2",
                        modifier = Modifier
                            .background(ColorDarkGreen)
                            .aspectRatio(2f)
                            .weight(1f),
                        onClick = {
                            onAction(DartsAction.Double(2))
                            navigateToStatsScreen()
                        }
                    )
                    GameButton(
                        symbol = "3",
                        modifier = Modifier
                            .background(ColorDarkGreen)
                            .aspectRatio(2f)
                            .weight(1f),
                        onClick = {
                            onAction(DartsAction.Double(3))
                            navigateToStatsScreen()
                        }
                    )
                }
            }
        }
    }
}

