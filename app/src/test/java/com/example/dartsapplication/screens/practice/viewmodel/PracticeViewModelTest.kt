package com.example.dartsapplication.screens.practice.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dartsapplication.entities.PracticeEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test



class PracticeViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var practiceViewModel: PracticeViewModel


    @Before
    fun setUp() {
        practiceViewModel = PracticeViewModel(FakePracticeRepository())
    }

    private fun generateMockEntity(): PracticeEntity {
        return PracticeEntity(
            1,
            "Test Entity",
            "501",
            "",
            "",
            "",
            0,
            false,
            0,
            0.0,
            false
        )
    }

    @Test
    fun `number entered and action taking setting entered score successful`(){
        practiceViewModel.enterNumber(5)
        practiceViewModel.enterNumber(0)
        val expected = "50"
        val actual = practiceViewModel.practiceState.enteredScore

        assertEquals(expected, actual)
    }

    @Test
    fun `number entered 3 characters accepted`(){
        practiceViewModel.enterNumber(1)
        practiceViewModel.enterNumber(0)
        practiceViewModel.enterNumber(0)
        val expected = "100"
        val actual = practiceViewModel.practiceState.enteredScore

        assertEquals(expected, actual)
    }

    @Test
    fun `number entered 4 characters remove all from entered score until empty string`(){
        practiceViewModel.enterNumber(1)
        practiceViewModel.enterNumber(1)
        practiceViewModel.enterNumber(1)
        practiceViewModel.enterNumber(1)
        val expected = "1"
        val actual = practiceViewModel.practiceState.enteredScore

        assertEquals(expected, actual)
    }

    @Test
    fun `delete performed on 3 digit number`(){
        practiceViewModel.enterNumber(100)
        practiceViewModel.performDelete()

        val expected = "10"
        val actual = practiceViewModel.practiceState.enteredScore

        assertEquals(expected, actual)
    }

    @Test
    fun `delete performed on 2 digit number`(){
        practiceViewModel.enterNumber(33)
        practiceViewModel.performDelete()

        val expected = "3"
        val actual = practiceViewModel.practiceState.enteredScore

        assertEquals(expected, actual)
    }

    @Test
    fun `delete performed on 1 digit number`(){
        practiceViewModel.enterNumber(5)
        practiceViewModel.performDelete()

        val expected = ""
        val actual = practiceViewModel.practiceState.enteredScore

        assertEquals(expected, actual)
    }

    @Test
    fun `delete performed when no score entered`(){
        practiceViewModel.performDelete()

        val expected = ""
        val actual = practiceViewModel.practiceState.enteredScore

        assertEquals(expected, actual)
    }


    @Test
    fun `if no darts thrown remaining score set to starting score`() {

        val game = generateMockEntity()
        practiceViewModel.insertPractice(game)
        practiceViewModel.getPractice()

        assertEquals("501", practiceViewModel.practiceGame.value.startingScore)

        practiceViewModel.setRemainingScore()
        val expected = "501"
        val actual = practiceViewModel.practiceState.remainingScore

        assertEquals(expected, actual)

//        practiceViewModel.insertPractice(game)
//        practiceViewModel.enterNumber(60)
//        val currentState = practiceViewModel.practiceState
//
//        assertEquals("60", currentState.enteredScore)
//        practiceViewModel.submitScore()



//        val gameEntity = practiceViewModel.practiceGame
//        val actual = gameEntity.value.name
//        val expected = "Test Entity"
//
//        //val actual = practiceViewModel.practiceState.enteredScore
//
//        assertEquals(expected, actual)


    }

    @Test
    fun `darts thrown is not 0 so remaining Score wont be set to starting`(){
        val game = generateMockEntity()
        practiceViewModel.insertPractice(game)
        practiceViewModel.getPractice()
        practiceViewModel.enterNumber(60)
        practiceViewModel.submitScore()
        val expected = "441"
        val actual = practiceViewModel.practiceState.remainingScore
        practiceViewModel.setRemainingScore()
        assertEquals(expected, actual)
    }

    @Test
    fun `check score is valid`(){
        assertTrue(PracticeViewModel.Companion.isScoreValid(501, 100))
        assertTrue(PracticeViewModel.Companion.isScoreValid(201, 85))
        assertTrue(PracticeViewModel.Companion.isScoreValid(100, 100))
        assertFalse(PracticeViewModel.Companion.isScoreValid(501, 181))
        assertFalse(PracticeViewModel.Companion.isScoreValid(100, 99))
        assertFalse(PracticeViewModel.Companion.isScoreValid(100, 101))
    }

    @Test
    fun `check if on double`(){
        assertTrue(PracticeViewModel.Companion.isOnDouble(40))
        assertTrue(PracticeViewModel.Companion.isOnDouble(170))
        assertFalse(PracticeViewModel.Companion.isOnDouble(168))
        assertFalse(PracticeViewModel.Companion.isOnDouble(159))
        assertFalse(PracticeViewModel.Companion.isOnDouble(200))
    }

    @Test
    fun `check if decimal rounded correctly`(){
        assertEquals(65.82, PracticeViewModel.Companion.roundOffDecimal(65.8210), 0.0)
        assertEquals(65.82, PracticeViewModel.Companion.roundOffDecimal(65.8200), 0.0)
        assertEquals(65.83, PracticeViewModel.Companion.roundOffDecimal(65.82854), 0.0)
        assertEquals(65.83, PracticeViewModel.Companion.roundOffDecimal(65.82500), 0.0)
    }

    @Test
    fun `check if leg complete`(){
        assertTrue(PracticeViewModel.Companion.legComplete(0))
        assertFalse(PracticeViewModel.Companion.legComplete(20))
    }






//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Test
//    fun `if no darts thrown remaining score set to starting score`() = runTest{
//        val entity = generateMockEntity()
//        practiceViewModel.insertPractice(entity)
//
//        practiceViewModel.getPractice()
//
//        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
//            practiceViewModel.practiceGame.collect {}
//        }
//
//        val actual = practiceViewModel.practiceGame.value.remainingScore
//
//
//        //val actual = practiceViewModel.practiceGame.value.remainingScore
//        val expected = "501"
//
//        assertEquals(expected, actual)
//        collectJob.cancel()
//    }



    @Test
    fun `once game started remaining score not changed to starting score`(){

    }

//    @Test
//    fun `insert shopping item with valid input, returns success`() {
//        val practiceEntity = PracticeEntity(1, "Insert Test", "", "", "",
//            "", 0, false, 0, 0.0, false)
//        practiceViewModel.insertPractice(practiceEntity)
//
//        val value = practiceViewModel.practiceGame.take(1).collect{
//            assertThat(it)
//                .contains(newPracticeGame)
//        }
//
//        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
//    }




















//    @ParameterizedTest
//    @MethodSource("onDoubleSource")
//    fun test_on_double(remainingScore: Int, onDouble: Boolean){
//        assertEquals(practiceViewModel.onDouble(remainingScore), onDouble)
//    }

//    @ParameterizedTest
//    @MethodSource("validSource")
//    fun scoreIsValid(currentScore: Int, enteredScore: Int) {
//        assertTrue(practiceViewModel.scoreValid(currentScore, enteredScore))
//    }
//
//
//
//    @ParameterizedTest
//    @MethodSource("invalidSource")
//    fun scoreIsInvalid(currentScore: Int, enteredScore: Int){
//        practiceViewModel = PracticeViewModel(repo)
//        assertFalse(practiceViewModel.scoreValid(currentScore, enteredScore))
//
//    }
//
//    companion object {
//        @JvmStatic
//        @Parameterized.Parameters
//        fun invalidSource() = listOf(
//            arrayOf(100, 120),
//            arrayOf(2, 4),
//            arrayOf(100, 99)
//        )
//
//        @JvmStatic
//        @Parameterized.Parameters
//        fun validSource() = listOf(
//            arrayOf(501, 180),
//            arrayOf(100, 100),
//            arrayOf(501, 160),
//            arrayOf(100, 98)
//        )
//
////        @JvmStatic
////        @Parameterized.Parameters
////        fun onDoubleSource() = listOf(
////            Arguments.of(150, true),
////            Arguments.of(180, false),
////            Arguments.of(170, true),
////            Arguments.of(169, false),
////            Arguments.of(168, false),
////            Arguments.of(166, false),
////            Arguments.of(165, false),
////            Arguments.of(163, false),
////            Arguments.of(162, false),
////            Arguments.of(159, false),
////            Arguments.of(140, true),
////            Arguments.of(100, true)
////        )
//    }




}