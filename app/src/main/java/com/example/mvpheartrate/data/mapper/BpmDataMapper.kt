package com.example.mvpheartrate.data.mapper

import com.example.mvpheartrate.data.local.db.result.model.BpmResultEntity
import com.example.mvpheartrate.domain.models.BpmData

fun BpmData.toBpmResultEntity(id: String): BpmResultEntity =
    BpmResultEntity(
        id = id,
        bpm = bpm,
        time = time
    )

fun BpmResultEntity.toBpmData(): BpmData =
    BpmData(
        bpm = bpm,
        time = time
    )