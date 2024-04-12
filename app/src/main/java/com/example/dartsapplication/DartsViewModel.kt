package com.example.dartsapplication
//
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//
//class DartsViewModel : ViewModel() {
//
//
//    var state by mutableStateOf(DartsState())
//        private set
//
//    fun onAction(action: DartsAction) {
//        when (action) {
//            is DartsAction.Number -> enterNumber(action.number)
//            is DartsAction.Submit -> submitScore()
//            is DartsAction.Delete -> performDelete()
//        }
//    }
//
//    private fun performDelete() {
//        if (state.enteredScore.isNotBlank()) {
//            state = state.copy(
//                enteredScore = state.enteredScore.dropLast(1)
//            )
//        }
//        return
//    }
//
//    private fun submitScore() {
//        val currentScore = state.currentScore.toDouble()
//        val enteredScore = state.enteredScore.toDouble()
//
//
//        if (currentScore == null || enteredScore == null) {
//            return
//        }
//        if (onDouble()) {
//            state = state.copy(
//                isOnDouble = true
//            )
//        }
//        if (scoreValid(currentScore, enteredScore)){
//            val newScore: Double = currentScore - enteredScore
//            val totalScored = 501 - newScore
//            val currentDartsThrown = state.dartsThrown + 3
//            var currentAverage: Double = totalScored/currentDartsThrown * 3
//            state = state.copy(
//                previousScore = state.enteredScore,
//                currentScore = newScore.toInt().toString(),
//                dartsThrown = state.dartsThrown + 3,
//                average = currentAverage.toString(),
//                enteredScore = ""
//            )
//        }
//
//    }
//
//    private fun scoreValid(currentScore: Double, enteredScore: Double): Boolean {
//        return enteredScore <= currentScore
//    }
//
//    private fun onDouble(): Boolean {
//        val remainingScore = state.currentScore.toDouble()
//        return (remainingScore <= 170 && !remainingScore.equals(169) || !remainingScore.equals(168)
//                || !remainingScore.equals(166) || !remainingScore.equals(165) || !remainingScore.equals(163)
//                || !remainingScore.equals(162) || !remainingScore.equals(159))
//    }
//
//    private fun enterNumber(number: Int) {
//        if (state.enteredScore.length >= 3) {
//            while (state.enteredScore.isNotBlank()) {
//                state = state.copy(
//                    enteredScore = state.enteredScore.dropLast(1)
//                )
//            }
//
//        }
//        state = state.copy(
//            enteredScore = state.enteredScore + number
//        )
//    }
//}