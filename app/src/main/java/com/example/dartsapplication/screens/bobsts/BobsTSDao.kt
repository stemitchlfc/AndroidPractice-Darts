package com.example.dartsapplication.screens.bobsts

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.dartsapplication.entities.BobsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BobsTSDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bobsEntity: BobsEntity)

    @Delete
    suspend fun delete(bobsEntity: BobsEntity)

    @Update
    suspend fun update(bobsEntity: BobsEntity)

    @Query("SELECT * FROM BobsEntity")
    fun getAllBobs(): Flow<List<BobsEntity>>

}