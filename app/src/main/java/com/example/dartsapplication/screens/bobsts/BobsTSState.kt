package com.example.dartsapplication.screens.bobsts

data class BobsTSState(
    val startingScore: String = "",
    val playerName: String = "",
    val remainingScore: String = "",
    val enteredScore: String = "",
    val average: String = "",
    val previousScore: String = "",
    val doublesHit: String = "",
    val missed: String = "",
    val totalThrown: String = "",
    val checkoutRate: Double = 0.0,
    val legComplete: Boolean = false
)