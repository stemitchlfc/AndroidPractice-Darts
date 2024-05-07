package com.example.dartsapplication.screens.bobsts

import com.example.dartsapplication.entities.BobsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface BobsTSRepository {

    suspend fun insert(bobsEntity: BobsEntity)

    suspend fun delete(bobsEntity: BobsEntity)

    suspend fun update(bobsEntity: BobsEntity)

    suspend fun getAllBobs(): Flow<List<BobsEntity>>


}

class RepositoryImpl @Inject constructor(
    private val dao: BobsTSDao,
) : BobsTSRepository {
    override suspend fun insert(bobsEntity: BobsEntity) {
        withContext(Dispatchers.IO) {
            dao.insert(bobsEntity)
        }
    }

    override suspend fun delete(bobsEntity: BobsEntity) {
        withContext(Dispatchers.IO) {
            dao.delete(bobsEntity)
        }
    }

    override suspend fun update(bobsEntity: BobsEntity) {
        withContext(Dispatchers.IO) {
            dao.update(bobsEntity)
        }
    }

    override suspend fun getAllBobs(): Flow<List<BobsEntity>> {
        return withContext(Dispatchers.IO) {
            dao.getAllBobs()
        }
    }


}