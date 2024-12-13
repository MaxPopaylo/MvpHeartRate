package com.example.mvpheartrate.data.local.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.mvpheartrate.data.PreferencesKeys.ONBOARDING_STATE_KEY
import com.example.mvpheartrate.domain.repository.OnboardingManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OnboardingManagerImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): OnboardingManager {

    override suspend fun getOnboardingState(): Boolean = dataStore
        .data.map { preferences ->
            preferences[ONBOARDING_STATE_KEY]
        }.first().toBoolean()

    override suspend fun setOnboardingState(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[ONBOARDING_STATE_KEY] = value.toString()
        }
    }

}