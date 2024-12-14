package com.example.mvpheartrate.domain.repository

import com.example.mvpheartrate.domain.models.BpmData
import kotlinx.coroutines.flow.Flow

interface ResultHistoryManager {
    suspend fun saveResult(bpmData: BpmData)
    suspend fun getAll(): Flow<List<BpmData>>
    suspend fun clearAll()
}