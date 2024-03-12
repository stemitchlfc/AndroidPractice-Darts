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
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PracticeViewModel @Inject constructor(
    private val repository: PracticeRepository,
) : ViewModel() {

    private val _practiceList = MutableStateFlow(emptyList<PracticeEntity>())
    val practiceList = _practiceList.asStateFlow()

    private val _practice = MutableStateFlow(PracticeEntity())
    val practice = _practice.asStateFlow()

    suspend fun getPractice(gameId: Int){
        viewModelScope.launch((Dispatchers.IO))
        {
            try {
                val practice: PracticeEntity = repository.getPractice(gameId)
                withContext(Dispatchers.Main){
                    _practice.value = practice
                }
            } catch (e: Exception){
                println("Exception getting")
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

    private val _currentScore = MutableStateFlow("")
    val currentScore = _currentScore.asStateFlow()
    fun setCurrentScore(currentScore: String) {
        _currentScore.tryEmit(currentScore)
    }

    private val _average = MutableStateFlow("")
    val average = _average.asStateFlow()
    fun setAverage(average: String) {
        _average.tryEmit(average)
    }

    var practiceState by mutableStateOf(PracticeState())
        private set

    fun onAction(action: DartsAction) {
        when (action) {
            is DartsAction.Number -> enterNumber(action.number)
            is DartsAction.Submit -> submitScore()
            is DartsAction.Delete -> performDelete()
        }
    }

    private fun performDelete() {
        if (practiceState.enteredScore.isNotBlank()) {
            practiceState = practiceState.copy(
                enteredScore = practiceState.enteredScore.dropLast(1)
            )
        }
        return
    }

    private fun submitScore() {
        if(practiceState.remainingScore.equals("")){
            practiceState.remainingScore = practiceState.startingScore
        }
        val currentScore = practiceState.remainingScore.toDouble()
        val enteredScore = practiceState.enteredScore.toDouble()


        if (onDouble()) {
            practiceState = practiceState.copy(
                isOnDouble = true
            )
        }
        if (scoreValid(currentScore, enteredScore)){
            val newScore: Double = currentScore - enteredScore
            val totalScored = 501 - newScore
            val currentDartsThrown = practiceState.dartsThrown + 3
            var currentAverage: Double = totalScored/currentDartsThrown * 3
            practiceState = practiceState.copy(
                previousScore = practiceState.enteredScore,
                remainingScore = newScore.toInt().toString(),
                dartsThrown = practiceState.dartsThrown + 3,
                average = currentAverage.toString(),
                enteredScore = ""
            )
        }

    }

    private fun scoreValid(currentScore: Double, enteredScore: Double): Boolean {
        return enteredScore <= currentScore
    }

    private fun onDouble(): Boolean {
        val remainingScore = practiceState.remainingScore.toDouble()
        return (remainingScore <= 170 && !remainingScore.equals(169) || !remainingScore.equals(168)
                || !remainingScore.equals(166) || !remainingScore.equals(165) || !remainingScore.equals(163)
                || !remainingScore.equals(162) || !remainingScore.equals(159))
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
            enteredScore = practiceState.enteredScore + number
        )
    }
//
//
//
//    private val _isChecked = MutableStateFlow(false)
//    val isChecked = _isChecked.asStateFlow()
//    fun setChecked(bool: Boolean) {
//        _isChecked.tryEmit(bool)
//    }

}