package com.example.dartsapplication.screens.practice.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dartsapplication.DartsAction
import com.example.dartsapplication.entities.PracticeEntity
import com.example.dartsapplication.screens.practice.PracticeRepository
import com.example.dartsapplication.screens.practice.PracticeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class PracticeViewModel @Inject constructor(
    private val repository: PracticeRepository,
) : ViewModel() {

    private val _practiceList = MutableStateFlow(emptyList<PracticeEntity>())
    val practiceList = _practiceList.asStateFlow()

    private val _practiceGame = MutableStateFlow(PracticeEntity())
    val practiceGame = _practiceGame.asStateFlow()


    fun getPractice() {
        viewModelScope.launch {
            repository.getAllPractices().collectLatest {
                _practiceGame.tryEmit(it.last())
            }
        }
    }


    fun getPracticeGames() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllPractices().collectLatest {
                _practiceList.tryEmit(it)
            }
        }
    }

    fun deletePractice(practiceEntity: PracticeEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(practiceEntity)
        }
    }

    fun updatePractice(practiceEntity: PracticeEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(practiceEntity)
        }
    }

    fun insertPractice(practiceEntity: PracticeEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(practiceEntity)
        }
    }


    private val _playerName = MutableStateFlow("")
    val playerName = _playerName.asStateFlow()
    fun setPlayerName(name: String) {
        _playerName.tryEmit(name)
    }


    private val _startingScore = MutableStateFlow("")
    val startingScore = _startingScore.asStateFlow()
    fun setStartingScore(startingScore: String) {
        _startingScore.tryEmit(startingScore)
    }


    var practiceState by mutableStateOf(PracticeState())
        private set

    fun onAction(action: DartsAction) {
        when (action) {
            is DartsAction.Number -> enterNumber(action.number)
            is DartsAction.Submit -> submitScore()
            is DartsAction.Delete -> performDelete()
            is DartsAction.Double -> calculateDoubles(action.double)
        }
    }

    private fun calculateDoubles(double: Int) {
        val toRemove: Int = 3 - double
        practiceState = practiceState.copy(
            dartsAtDouble = practiceState.dartsAtDouble + double,
            dartsThrown = practiceState.dartsThrown - toRemove,
            average = calculateAverage(toRemove)
        )
        val practiceEntity = generateEntity()

        updatePractice(practiceEntity)
        finishGame()
    }

    private fun generateEntity(): PracticeEntity {
        return PracticeEntity(
            id = practiceGame.value.id,
            name = practiceGame.value.name,
            startingScore = practiceGame.value.startingScore,
            previousScore = practiceState.previousScore,
            remainingScore = practiceState.remainingScore,
            average = practiceState.average,
            dartsThrown = practiceState.dartsThrown,
            isOnDouble = practiceState.isOnDouble,
            legComplete = practiceState.legComplete,
            dartsAtDouble = practiceState.dartsAtDouble
        )
    }

    private fun performDelete() {
        if (practiceState.enteredScore.isNotBlank()) {
            practiceState = practiceState.copy(
                enteredScore = practiceState.enteredScore.dropLast(1)
            )
        }
        return
    }

    private fun calculateAverage(toRemove: Int):String{
        val totalScored = practiceGame.value.startingScore.toDouble()
        val currentDartsThrown = practiceState.dartsThrown - toRemove
        val notRoundedAverage: Double = totalScored / currentDartsThrown * 3
        val average = roundOffDecimal(notRoundedAverage)
        return average.toString()
    }

    private fun submitScore() {
        if (practiceState.dartsThrown == 0) {
            practiceState = practiceState.copy(
                remainingScore = practiceGame.value.startingScore
            )
            //practiceState.remainingScore = practiceState.startingScore
        }
        val currentScore = practiceState.remainingScore.toDouble()
        val enteredScore = practiceState.enteredScore.toDouble()


        if (scoreValid(currentScore, enteredScore)) {
            val newScore: Double = currentScore - enteredScore
            val totalScored = practiceGame.value.startingScore.toDouble() - newScore
            val currentDartsThrown = practiceState.dartsThrown + 3
            val currentAverage: Double = totalScored / currentDartsThrown * 3

            practiceState = practiceState.copy(
                previousScore = practiceState.enteredScore,
                remainingScore = newScore.toInt().toString(),
                dartsThrown = practiceState.dartsThrown + 3,
                average = currentAverage.toString(),
                enteredScore = "",
                //legComplete = legComplete
            )
            if (onDouble()) {
                practiceState = practiceState.copy(
                    isOnDouble = true
                )
            }
            if (legComplete()) {
                //finishGame()
                practiceState = practiceState.copy(
                    isOnDouble = false,
                    legComplete = true
                )
            }
            val practiceEntity = PracticeEntity(
                id = practiceGame.value.id,
                name = practiceGame.value.name,
                startingScore = practiceGame.value.startingScore,
                previousScore = practiceState.previousScore,
                remainingScore = practiceState.remainingScore,
                average = practiceState.average,
                dartsThrown = practiceState.dartsThrown,
                isOnDouble = practiceState.isOnDouble,
                legComplete = practiceState.legComplete
            )
            updatePractice(practiceEntity)
        }
    }

    private fun finishGame() {

    }

    private fun legComplete(): Boolean {
        val remainingScore = practiceState.remainingScore.toDouble()
        return remainingScore == 0.0
    }

    private fun scoreValid(currentScore: Double, enteredScore: Double): Boolean {
        return enteredScore <= currentScore
    }

    private fun onDouble(): Boolean {
        val remainingScore = practiceState.remainingScore.toDouble()
        return (remainingScore <= 170)
    }

    private fun enterNumber(number: Int) {
        if (practiceState.enteredScore.length >= 3) {
            while (practiceState.enteredScore.isNotBlank()) {
                practiceState = practiceState.copy(
                    enteredScore = practiceState.enteredScore.dropLast(1)
                )
            }

        }
        practiceState = practiceState.copy(
            enteredScore = practiceState.enteredScore + number,
            remainingScore = practiceState.remainingScore
        )
    }


    fun roundOffDecimal(number: Double): Double? {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(number).toDouble()
    }
}