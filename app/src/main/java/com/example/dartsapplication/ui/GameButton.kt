package com.example.dartsapplication.ui

import  androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.dartsapplication.ui.theme.ColorLighterGreen
import com.example.dartsapplication.ui.theme.DartsApplicationTheme

@Composable
fun GameButton(
    symbol: String,
    modifier: Modifier,
    onClick: () -> Unit
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onClick() }
                .then(modifier)
        ) {
            Text(
                text = symbol,
                fontSize = 36.sp,
                color = Color.DarkGray
            )
        }
}

@Preview(showBackground = true)
@Composable
fun PreviewGameButton(){
    DartsApplicationTheme {
        GameButton(symbol = "1", modifier = Modifier
            .background(color = ColorLighterGreen)) {
        }
    }
}