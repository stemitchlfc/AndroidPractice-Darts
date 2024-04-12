package com.example.dartsapplication

sealed class DartsAction{

    data class Number(val number: Int): DartsAction()

    data class Double(val double: Int): DartsAction()
    object Submit: DartsAction()
    object Delete: DartsAction()

}
