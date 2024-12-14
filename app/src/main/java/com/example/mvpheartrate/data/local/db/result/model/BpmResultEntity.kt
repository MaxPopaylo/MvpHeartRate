package com.example.mvpheartrate.data.local.db.result.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity("result_history")
data class BpmResultEntity(
    @PrimaryKey val id: String,
    val bpm: Int,
    val time: LocalDateTime
)
