package com.example.mvpheartrate.domain.repository

interface OnboardingManager {
    suspend fun getOnboardingState(): Boolean
    suspend fun setOnboardingState(value: Boolean)
}