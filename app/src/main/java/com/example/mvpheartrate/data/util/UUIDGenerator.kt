package com.example.mvpheartrate.data.util

import java.util.UUID

object UUIDGenerator {
    fun generate(): String = UUID.randomUUID().toString()
}