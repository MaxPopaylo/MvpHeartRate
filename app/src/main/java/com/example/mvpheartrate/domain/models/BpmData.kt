package com.example.mvpheartrate.domain.models

import com.example.mvpheartrate.domain.serializer.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class BpmData (
    val bpm: Int,
    @Serializable(with = LocalDateTimeSerializer::class) val time: LocalDateTime
)