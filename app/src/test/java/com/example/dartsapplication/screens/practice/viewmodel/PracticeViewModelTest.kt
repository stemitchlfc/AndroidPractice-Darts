package com.example.dartsapplication.screens.practice.viewmodel

import com.example.dartsapplication.screens.practice.PracticeRepository
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.Mock


@RunWith(Parameterized::class)
class PracticeViewModelTest{

    private lateinit var practiceViewModel: PracticeViewModel


    @Mock
    lateinit var repo: PracticeRepository

    @Before
    fun setUp() {
        practiceViewModel = PracticeViewModel(repo)
    }


//    @ParameterizedTest
//    @MethodSource("onDoubleSource")
//    fun test_on_double(remainingScore: Int, onDouble: Boolean){
//        assertEquals(practiceViewModel.onDouble(remainingScore), onDouble)
//    }

    @ParameterizedTest
    @MethodSource("validSource")
    fun scoreIsValid(currentScore: Int, enteredScore: Int) {
        assertTrue(practiceViewModel.scoreValid(currentScore, enteredScore))
    }



    @ParameterizedTest
    @MethodSource("invalidSource")
    fun scoreIsInvalid(currentScore: Int, enteredScore: Int){
        practiceViewModel = PracticeViewModel(repo)
        assertFalse(practiceViewModel.scoreValid(currentScore, enteredScore))

    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun invalidSource() = listOf(
            arrayOf(100, 120),
            arrayOf(2, 4),
            arrayOf(100, 99)
        )

        @JvmStatic
        @Parameterized.Parameters
        fun validSource() = listOf(
            arrayOf(501, 180),
            arrayOf(100, 100),
            arrayOf(501, 160),
            arrayOf(100, 98)
        )

//        @JvmStatic
//        @Parameterized.Parameters
//        fun onDoubleSource() = listOf(
//            Arguments.of(150, true),
//            Arguments.of(180, false),
//            Arguments.of(170, true),
//            Arguments.of(169, false),
//            Arguments.of(168, false),
//            Arguments.of(166, false),
//            Arguments.of(165, false),
//            Arguments.of(163, false),
//            Arguments.of(162, false),
//            Arguments.of(159, false),
//            Arguments.of(140, true),
//            Arguments.of(100, true)
//        )
    }




}