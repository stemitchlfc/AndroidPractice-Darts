package com.example.dartsapplication.screens.practice
//
//import com.example.dartsapplication.entities.PracticeEntity
//import java.math.RoundingMode
//import java.text.DecimalFormat
//
//class PracticeGame {
//
//    companion object {
//        fun play(practiceState: PracticeState): PracticeState {
//            val currentScore = practiceState.remainingScore.toInt()
//            val enteredScore = practiceState.enteredScore.toInt()
//            if (scoreValid(currentScore
//                    , enteredScore)){
//
//            }
//            if (onDouble(practiceState.remainingScore.toInt())){
//                practiceState = practiceState.copy(
//                    isOnDouble = true
//                )
//            }
//            if (legComplete(practiceState.remainingScore.toInt())){
//                practiceState.isOnDouble = false
//                practiceState.legComplete = true
//            }
//            return practiceState
//        }
//
//        fun scoreValid(currentScore: Int, enteredScore: Int): Boolean {
//            return !(enteredScore > 180 ||
//                    enteredScore > currentScore ||
//                    currentScore - enteredScore == 1)
//        }
//
//        fun onDouble(remainingScore: Int): Boolean {
//            return (remainingScore <= 170 && remainingScore != 169 && remainingScore != 168
//                    && remainingScore != 166 && remainingScore != 165 && remainingScore != 163
//                    && remainingScore != 162 && remainingScore != 159)
//        }
//
//        private fun roundOffDecimal(number: Double): Double {
//            val df = DecimalFormat("#.##")
//            df.roundingMode = RoundingMode.CEILING
//            return df.format(number).toDouble()
//        }
//
//        private fun legComplete(remainingScore: Int): Boolean {
//            return remainingScore == 0
//        }
//
//        private fun calculateAverage(toRemove: Int):String{
//            val totalScored = practiceGame.value.startingScore.toDouble() - practiceState.remainingScore.toDouble()
//            val currentDartsThrown = practiceState.dartsThrown - toRemove
//            val notRoundedAverage: Double = totalScored / currentDartsThrown * 3
//            val average = roundOffDecimal(notRoundedAverage)
//            return average.toString()
//        }
//
//    }
//
//}