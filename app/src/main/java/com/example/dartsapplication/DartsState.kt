package com.example.dartsapplication



data class DartsState(
    val currentScore: String = "501",
    val enteredScore: String = "",
    val average: String = "",
    val previousScore: String = "",
    val dartsThrown: Int = 0,
    val isOnDouble: Boolean = false
)