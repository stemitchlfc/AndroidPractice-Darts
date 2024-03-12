package com.example.dartsapplication.screens.practice

import com.example.dartsapplication.entities.PracticeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface PracticeRepository {

    suspend fun insert(practiceEntity: PracticeEntity)

    suspend fun delete(practiceEntity: PracticeEntity)

    suspend fun update(practiceEntity: PracticeEntity)

    suspend fun getAllPractices(): Flow<List<PracticeEntity>>

    suspend fun getPractice(gameId: Int): PracticeEntity
}

class RepositoryImpl @Inject constructor(
    private val dao: PracticeDao,
) : PracticeRepository {
    override suspend fun insert(practiceEntity: PracticeEntity) {
        withContext(Dispatchers.IO) {
            dao.insert(practiceEntity)
        }
    }

    override suspend fun delete(practiceEntity: PracticeEntity) {
        withContext(Dispatchers.IO) {
            dao.delete(practiceEntity)
        }
    }

    override suspend fun update(practiceEntity: PracticeEntity) {
        withContext(Dispatchers.IO) {
            dao.update(practiceEntity)
        }
    }

    override suspend fun getAllPractices(): Flow<List<PracticeEntity>> {
        return withContext(Dispatchers.IO) {
            dao.getAllPractices()
        }
    }

    override suspend fun getPractice(gameId: Int): PracticeEntity {
        return dao.getPractice(1)
    }

}