package com.example.mvpheartrate.data

import androidx.datastore.preferences.core.stringPreferencesKey

internal object PreferencesKeys {
    val ONBOARDING_STATE_KEY = stringPreferencesKey("onboarding_state_key")
    const val ONBOARDING_PREFERENCES = "onboarding_preferences"
}