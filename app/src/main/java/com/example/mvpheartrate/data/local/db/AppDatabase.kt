package com.example.mvpheartrate.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvpheartrate.data.local.db.result.dao.BpmResultDao
import com.example.mvpheartrate.data.local.db.result.model.BpmResultEntity

@Database(
    entities = [BpmResultEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract val bpmResultDao: BpmResultDao
}