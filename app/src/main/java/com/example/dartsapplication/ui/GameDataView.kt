package com.example.dartsapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.dartsapplication.ui.theme.ColorLighterGreen
import com.example.dartsapplication.ui.theme.DartsApplicationTheme

@Composable
fun GameDataView(data: String,
                 dataType: String,
                 modifier: Modifier
) {
    Column(
        modifier = Modifier
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                .background(ColorLighterGreen)
                .fillMaxWidth()
            ) {
                Text(
                    text = dataType,
                    fontSize = 8.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }

        Row(modifier = Modifier) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                .fillMaxWidth()
                .background(ColorLighterGreen)) {
                Text(
                    text = data,
                    fontSize = 36.sp,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }

}


@Preview
@Composable
fun DisplayGameDataView(){
    DartsApplicationTheme {
        GameDataView(
            data = "321",
            modifier = Modifier,
            dataType = "Current Score"
        )
    }
}