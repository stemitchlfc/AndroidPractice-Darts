package com.example.dartsapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.dartsapplication.DartsAction
import com.example.dartsapplication.ui.theme.ColorDarkGreen
import com.example.dartsapplication.ui.theme.ColorOrange
import com.example.dartsapplication.ui.theme.DartsApplicationTheme

@Composable
fun GameButtons(
    modifier: Modifier = Modifier,
    buttonSpacing: Dp = 8.dp,
    onAction: (DartsAction) -> Unit
){
    Box(modifier = Modifier.fillMaxHeight()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            Row(modifier = Modifier) {
                GameButton(symbol = "1", modifier = Modifier
                    .background(ColorDarkGreen)
                    .aspectRatio(2f)
                    .weight(1f),
                    onClick = {
                        onAction(DartsAction.Number(1))
                    }
                )
                GameButton(symbol = "2", modifier = Modifier
                    .background(ColorDarkGreen)
                    .aspectRatio(2f)
                    .weight(1f),
                    onClick = {
                        onAction(DartsAction.Number(2))
                    }
                )
                GameButton(symbol = "3", modifier = Modifier
                    .background(ColorDarkGreen)
                    .aspectRatio(2f)
                    .weight(1f),
                    onClick = {
                        onAction(DartsAction.Number(3))
                    }
                )
            }
            Row(modifier = Modifier) {
                GameButton(symbol = "4", modifier = Modifier
                    .background(ColorDarkGreen)
                    .aspectRatio(2f)
                    .weight(1f),
                    onClick = {
                        onAction(DartsAction.Number(4))
                    }
                )
                GameButton(symbol = "5", modifier = Modifier
                    .background(ColorDarkGreen)
                    .aspectRatio(2f)
                    .weight(1f),
                    onClick = {
                        onAction(DartsAction.Number(5))
                    }
                )
                GameButton(symbol = "6", modifier = Modifier
                    .background(ColorDarkGreen)
                    .aspectRatio(2f)
                    .weight(1f),
                    onClick = {
                        onAction(DartsAction.Number(6))
                    }
                )
            }
            Row(modifier = Modifier) {
                GameButton(symbol = "7", modifier = Modifier
                    .background(ColorDarkGreen)
                    .aspectRatio(2f)
                    .weight(1f),
                    onClick = {
                        onAction(DartsAction.Number(7))
                    }
                )
                GameButton(symbol = "8", modifier = Modifier
                    .background(ColorDarkGreen)
                    .aspectRatio(2f)
                    .weight(1f),
                    onClick = {
                        onAction(DartsAction.Number(8))
                    }
                )
                GameButton(symbol = "9", modifier = Modifier
                    .background(ColorDarkGreen)
                    .aspectRatio(2f)
                    .weight(1f),
                    onClick = {
                        onAction(DartsAction.Number(9))
                    }
                )
            }
            Row(modifier = Modifier) {
                GameButton(symbol = "DEL", modifier = Modifier
                    .background(ColorOrange)
                    .aspectRatio(2f)
                    .weight(1f),
                    onClick = {
                        onAction(DartsAction.Delete)
                    }
                )
                GameButton(symbol = "0", modifier = Modifier
                    .background(ColorDarkGreen)
                    .aspectRatio(2f)
                    .weight(1f),
                    onClick = {
                        onAction(DartsAction.Number(0))
                    }
                )
                GameButton(symbol = "ENT", modifier = Modifier
                    .background(ColorOrange)
                    .aspectRatio(2f)
                    .weight(1f),
                    onClick = {
                        onAction(DartsAction.Submit)
                    }
                )
            }
        }
    }
}


@Preview
@Composable
fun DisplayGameButtons(){
    DartsApplicationTheme {
        GameButtons(modifier = Modifier, onAction = {})
    }
}