package com.example.mvpheartrate.data.local.repository

import com.example.mvpheartrate.data.local.db.AppDatabase
import com.example.mvpheartrate.data.mapper.toBpmData
import com.example.mvpheartrate.data.mapper.toBpmResultEntity
import com.example.mvpheartrate.data.util.UUIDGenerator
import com.example.mvpheartrate.domain.models.BpmData
import com.example.mvpheartrate.domain.repository.ResultHistoryManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ResultHistoryManagerImpl @Inject constructor(
    private val database: AppDatabase
): ResultHistoryManager {
    override suspend fun saveResult(bpmData: BpmData) {
        val id = UUIDGenerator.generate()
        database.bpmResultDao.insert(
            bpmData.toBpmResultEntity(id)
        )
    }

    override suspend fun getAll(): Flow<List<BpmData>> =
        database.bpmResultDao.getAll()
            .map { entities ->
                entities.map { it.toBpmData() }
            }

    override suspend fun clearAll() {
        database.bpmResultDao.clearAll()
    }
}