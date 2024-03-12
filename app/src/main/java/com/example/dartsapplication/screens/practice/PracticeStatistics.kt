package com.example.dartsapplication.screens.practice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dartsapplication.screens.practice.viewmodel.PracticeViewModel
import com.example.dartsapplication.ui.theme.ColorDarkGreen
import com.example.dartsapplication.ui.theme.ColorLighterGreen

@Composable
fun PracticeStatistics(
    practiceViewModel: PracticeViewModel
) {

    LaunchedEffect(key1 = true, block = {
        practiceViewModel.getPracticeGames()
    })

    val contents by practiceViewModel.practiceList.collectAsStateWithLifecycle()

    val modifier = Modifier
        .padding(15.dp)
        .fillMaxWidth()
        .height(80.dp)


    LazyColumn(content = {

        items(contents) {
            val item = ImutablePractice(it)
            PracticeCard(wrapper = item, modifier = modifier)
        }
    }, modifier = Modifier.fillMaxSize()
        .background(ColorLighterGreen)
    )


}

@Composable
fun PracticeCard(
    wrapper: ImutablePractice,
    modifier: Modifier,
) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
                .background(ColorDarkGreen),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(3f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {


                wrapper.practiceEntity.name?.let {
                    Text(
                        text = it,
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = wrapper.practiceEntity.startingScore,
                    maxLines = 1,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black
                )
            }

        }
    }
}


