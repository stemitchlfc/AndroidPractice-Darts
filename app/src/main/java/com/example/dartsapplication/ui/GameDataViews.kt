package com.example.dartsapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.dartsapplication.ui.theme.ColorDarkGreen
import com.example.dartsapplication.ui.theme.DartsApplicationTheme

@Composable
fun GameDataViews(
    modifier: Modifier = Modifier,
    displaySpacing: Dp = 8.dp
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .wrapContentHeight(Alignment.CenterVertically)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            Row(modifier = Modifier.width(IntrinsicSize.Max)) {
                GameDataView(
                    data = "321",
                    dataType = "Current Score",
                    modifier = Modifier
                )
            }
            Row(modifier = Modifier) {
                GameDataView(
                    data = "82",
                    dataType = "Average",
                    modifier = Modifier.width(IntrinsicSize.Max)
                        .background(ColorDarkGreen)
                )
                GameDataView(
                    data = "100",
                    dataType = "",
                    modifier = Modifier
                        .background(ColorDarkGreen)
                        .aspectRatio(1f)
                        .weight(1f)
                )
                GameDataView(
                    data = "60",
                    dataType = "Previous Score",
                    modifier = Modifier
                        .background(ColorDarkGreen)
                        .aspectRatio(1f)
                        .weight(1f)
                )
            }
        }
    }
}

@Preview
@Composable
fun DisplayGameDataDisplay(){
    DartsApplicationTheme {
        GameDataViews()
    }
}