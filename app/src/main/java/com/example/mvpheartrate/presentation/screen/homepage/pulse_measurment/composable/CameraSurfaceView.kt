package com.example.mvpheartrate.presentation.screen.homepage.pulse_measurment.composable

import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun CameraSurfaceView(
    onSurfaceCreated: (SurfaceView) -> Unit,
    onDispose: () -> Unit
) {
    val context = LocalContext.current

    AndroidView(factory = {
        val surfaceView = SurfaceView(context)
        surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                onSurfaceCreated(surfaceView)
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                onDispose()
            }
        })

        surfaceView
    })
}
