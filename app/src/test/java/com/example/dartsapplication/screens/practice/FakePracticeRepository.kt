package com.example.dartsapplication.screens.practice.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.dartsapplication.entities.PracticeEntity
import com.example.dartsapplication.screens.practice.PracticeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePracticeRepository: PracticeRepository {

    private val practiceEntities = mutableListOf<PracticeEntity>()

    val gameToInsert = PracticeEntity(1, "Test Game", "501", "", "" +
            "", "", 0, false, 0, 0.0, false)

    private val observablePracticeEntity = MutableLiveData<List<PracticeEntity>>(practiceEntities)

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData() {
        observablePracticeEntity.postValue(practiceEntities)

    }

    override suspend fun insert(practiceEntity: PracticeEntity) {
        practiceEntities.add(practiceEntity)
        refreshLiveData()
    }

    override suspend fun delete(practiceEntity: PracticeEntity) {
        practiceEntities.remove(practiceEntity)
        refreshLiveData()
    }

    override suspend fun update(practiceEntity: PracticeEntity) {
        practiceEntities.add(practiceEntity)
        refreshLiveData()
    }

    override suspend fun getAllPractices(): Flow<List<PracticeEntity>> {
        return flow { emit(listOf(gameToInsert)) }
    }
}