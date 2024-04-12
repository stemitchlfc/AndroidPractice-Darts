package com.example.dartsapplication.screens.practice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dartsapplication.entities.PracticeEntity
import com.example.dartsapplication.screens.practice.viewmodel.PracticeViewModel
import com.example.dartsapplication.ui.theme.ColorDarkGreen
import com.example.dartsapplication.ui.theme.ColorDarkGrey
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
        .background(ColorDarkGreen)


    LazyColumn(
        content = {

            items(contents) {
                val item = ImmutablePractice(it)
                PracticeCard(
                    wrapper = item,
                    modifier = modifier,
                    deleteGame = {
                        practiceViewModel.deletePractice(it)
                    }
                )
            }
        }, modifier = Modifier
            .fillMaxSize()
            .background(ColorLighterGreen)
    )


}

@Composable
fun PracticeCard(
    wrapper: ImmutablePractice,
    modifier: Modifier,
    deleteGame: () -> Unit
) {


    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorDarkGreen),
            verticalAlignment = Alignment.CenterVertically
        )
        {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(ColorDarkGreen),
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(8.dp)
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
                            Text(
                                text = "Average: ${wrapper.practiceEntity.average}",
                                maxLines = 1,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .background(ColorDarkGreen)
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier
                                .background(ColorDarkGreen)
                        ) {

                            Text(
                                text = "Darts Thrown: ${wrapper.practiceEntity.dartsThrown.toString()}",
                                maxLines = 1,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )
                            Button(
                                onClick = {
                                    deleteGame()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = ColorDarkGrey,
                                    contentColor = Color.White
                                ),

                                ) {
                                Text(text = "Delete")
                            }
                        }
                    }
                }
            }

        }

}

@Preview
@Composable
fun PreviewPracticeCard() {
    val practiceEntity = PracticeEntity(
        id = 1,
        name = "Ste Preview",
        startingScore = "501",
        average = "75",
        dartsThrown = 20
    )

    PracticeCard(wrapper = ImmutablePractice(practiceEntity), modifier = Modifier) {

    }
}



@Immutable
data class ImmutablePractice(val practiceEntity: PracticeEntity)
