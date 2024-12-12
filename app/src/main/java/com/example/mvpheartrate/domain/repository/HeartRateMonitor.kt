package com.example.mvpheartrate.domain.repository

import android.view.SurfaceView
import com.example.mvpheartrate.domain.models.BpmData
import kotlinx.coroutines.CoroutineScope

interface HeartRateMonitor {
    suspend fun subscribe(
        coroutineScope: CoroutineScope,
        surfaceView: SurfaceView,
        onBpmStateChange: (Int) -> Unit,
        onFingerDetectedState: (Boolean) -> Unit,
        onResult: (BpmData) -> Unit
    )

    suspend fun unsubscribe()
}