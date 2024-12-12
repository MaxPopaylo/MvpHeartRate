package com.example.mvpheartrate.data.di

import com.example.mvpheartrate.data.util.HeartRateMonitorImpl
import com.example.mvpheartrate.domain.repository.HeartRateMonitor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideHeartRateMonitor(): HeartRateMonitor =
        HeartRateMonitorImpl()

}