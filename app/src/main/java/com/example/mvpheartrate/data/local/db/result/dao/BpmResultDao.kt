package com.example.mvpheartrate.data.local.db.result.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvpheartrate.data.local.db.result.model.BpmResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BpmResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: BpmResultEntity)

    @Query("SELECT * FROM result_history")
    suspend fun getAll(): Flow<List<BpmResultEntity>>

    @Query("DELETE FROM result_history")
    suspend fun clearAll()
}