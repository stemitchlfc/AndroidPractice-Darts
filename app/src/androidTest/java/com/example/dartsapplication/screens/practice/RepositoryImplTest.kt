package com.example.dartsapplication.screens.practice

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.dartsapplication.entities.PracticeEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class RepositoryImplTest {

    private lateinit var database: PracticeDatabase
    private lateinit var dao: PracticeDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PracticeDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.practiceDao
    }

    @After
    fun tearDown() {
        database.close()
    }

    private fun createPracticeEntity(): PracticeEntity {
        return PracticeEntity(
            1,
            "Ste Mitchell",
            "501",
            "501",
            "0",
            "0",
            0,
            false,
            0,
            0.0,
            false
        )
    }

    @Test
    fun insertPracticeGame_gameListedTrue() = runTest {
        val practiceGame = createPracticeEntity()
        dao.insert(practiceGame)
        dao.getAllPractices().take(1).collect {
            assertThat(it)
                .contains(practiceGame)
        }
    }

    @Test
    fun insertPracticeGame_gameListedFalse() = runTest {
        val practiceGame = createPracticeEntity()
        dao.insert(practiceGame)

        val falseGame = PracticeEntity(2, "False Game", "501", "", "", "", 0, false, 0, 0.0, false)

        dao.getAllPractices().take(1).collect {
            assertThat(it)
                .doesNotContain(falseGame)
        }
    }

    @Test
    fun deletePracticeGame_gameDeleted() = runTest {
        val practiceGame = createPracticeEntity()
        dao.insert(practiceGame)

        dao.delete(practiceGame)

        dao.getAllPractices().take(1).collect{
            assertThat(it)
                .doesNotContain(practiceGame)
        }
    }

    @Test
    fun update_score_and_average() = runTest {
        val practiceGame = createPracticeEntity()
        dao.insert(practiceGame)
        val newPracticeGame = PracticeEntity(1, "Ste Mitchell", "501", "401", "100", "100", 3, false, 0, 0.0, false)
        dao.update(newPracticeGame)
        dao.getAllPractices().take(1).collect{
            assertThat(it)
                .doesNotContain(practiceGame)
            assertThat(it)
                .contains(newPracticeGame)
        }

    }


}