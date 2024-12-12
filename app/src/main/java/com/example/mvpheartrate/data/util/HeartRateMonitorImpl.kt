package com.example.mvpheartrate.data.util

import android.os.CountDownTimer
import android.util.Log
import android.view.SurfaceView
import com.example.mvpheartrate.domain.models.BpmData
import com.example.mvpheartrate.domain.repository.HeartRateMonitor
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import net.kibotu.heartrateometer.HeartRateOmeter
import net.kibotu.kalmanrx.jama.Matrix
import net.kibotu.kalmanrx.jkalman.JKalman
import java.time.LocalDateTime

class HeartRateMonitorImpl: HeartRateMonitor {

    private var bpmList = mutableListOf<Int>()
    private var lastBpm = 0
    private var timer: CountDownTimer? = null
    private var result = 0

    private var bpmUpdates: Disposable? = null

    override suspend fun unsubscribe() {
        reset()
        dispose()
    }

     override suspend fun subscribe(
         coroutineScope: CoroutineScope,
        surfaceView: SurfaceView,
        onBpmStateChange: (Int) -> Unit,
        onFingerDetectedState: (Boolean) -> Unit,
        onResult: (BpmData) -> Unit
    ) {
        bpmList.clear()
        timer?.cancel()

         val kalman = JKalman(2, 1)
         val m = Matrix(1, 1)
         val tr = arrayOf(doubleArrayOf(1.0, 0.0), doubleArrayOf(0.0, 1.0))
         kalman.transition_matrix = Matrix(tr)
         kalman.error_cov_post = kalman.error_cov_post.identity()

         bpmUpdates = HeartRateOmeter()
             .withAverageAfterSeconds(3)
             .setFingerDetectionListener { detected ->
                 coroutineScope.launch {
                     onFingerDetectedState(detected)
                     if (detected) {
                         startCounting(
                             onResult = {
                                 onResult(it)
                             }
                         )
                     } else {
                         reset()
                     }
                 }
             }
             .bpmUpdates(surfaceView)
             .subscribe({ bpmData ->
                 coroutineScope.launch {
                     if (bpmData.value != 0) {
                         m.set(0, 0, bpmData.value.toDouble())
                         kalman.Predict()
                         val c = kalman.Correct(m)
                         val bpm = bpmData.copy(value = c.get(0, 0).toInt())

                         lastBpm = bpm.value
                         onBpmStateChange(bpm.value)
                     }
                 }
             }, { throwable ->
                 Log.e("HeartRateMeasurement", "Error in BPM updates", throwable)
                 dispose()
             })

    }

    private fun startCounting(
        onResult: (BpmData) -> Unit
    ) {
        timer = object : CountDownTimer(15000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (lastBpm != 0) {
                    bpmList.add(lastBpm)
                    Log.d("HeartRateMeasurement", "BPM: $bpmList")
                }
            }

            override fun onFinish() {
                result = bpmList.average().toInt()
                onResult(createBpmData(result))
                dispose()
                Log.d("HeartRateMeasurement", "Result: $result")
            }
        }.start()
    }

    private fun reset() {
        result = 0
        bpmList.clear()
        timer?.cancel()
    }

    private fun dispose() {
        timer?.cancel()
        bpmUpdates?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
        bpmUpdates = null
    }

    private fun createBpmData(bpm: Int) =
        BpmData(
            bpm = bpm,
            time = LocalDateTime.now()
        )
}