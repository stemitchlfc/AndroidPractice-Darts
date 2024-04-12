package com.example.dartsapplication.screens.practice

data class PracticeState(
    val startingScore: String = "",
    val playerName: String = "",
    val remainingScore: String = "",
    val enteredScore: String = "",
    val average: String = "",
    val previousScore: String = "",
    val dartsThrown: Int = 0,
    val isOnDouble: Boolean = false,
    val dartsAtDouble: Int = 0,
    val checkoutRate: Double = 0.0,
    val legComplete: Boolean = false
)

