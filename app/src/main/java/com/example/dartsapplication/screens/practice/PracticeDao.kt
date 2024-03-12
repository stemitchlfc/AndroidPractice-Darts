package com.example.dartsapplication.screens.practice

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.dartsapplication.entities.PracticeEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface PracticeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(practiceEntity: PracticeEntity)

    @Delete
    suspend fun delete(practiceEntity: PracticeEntity)

    @Update
    suspend fun update(practiceEntity: PracticeEntity)

    @Query("SELECT * FROM PracticeEntity")
    fun getAllPractices(): Flow<List<PracticeEntity>>

    @Query("SELECT * FROM PracticeEntity WHERE id=:gameId")
    fun getPractice(gameId: Int): PracticeEntity
}