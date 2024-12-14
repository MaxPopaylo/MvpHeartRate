package com.example.mvpheartrate.presentation.common.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object LocalDateTimeFormatter {
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    fun getTime(dateTime: LocalDateTime): String = dateTime.format(timeFormatter)
    fun getDate(dateTime: LocalDateTime): String = dateTime.format(dateFormatter)
}