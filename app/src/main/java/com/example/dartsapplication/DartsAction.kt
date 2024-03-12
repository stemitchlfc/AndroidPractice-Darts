package com.example.dartsapplication

sealed class DartsAction{

    data class Number(val number: Int): DartsAction()
    object Submit: DartsAction()
    object Delete: DartsAction()

}
