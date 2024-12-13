package com.example.mvpheartrate.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.example.mvpheartrate.data.PreferencesKeys.ONBOARDING_PREFERENCES
import com.example.mvpheartrate.data.local.db.AppDatabase
import com.example.mvpheartrate.data.local.repository.OnboardingManagerImpl
import com.example.mvpheartrate.data.util.HeartRateMonitorImpl
import com.example.mvpheartrate.domain.repository.HeartRateMonitor
import com.example.mvpheartrate.domain.repository.OnboardingManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    // MARK: - Repository implementation modules
    @Provides
    @Singleton
    fun provideHeartRateMonitor(): HeartRateMonitor =
        HeartRateMonitorImpl()

    @Provides
    @Singleton
    fun provideOnboardingManager(dataStore: DataStore<Preferences>): OnboardingManager =
        OnboardingManagerImpl(dataStore)


    // MARK: - DataStorePreferences
    @Provides
    @Singleton
    fun provideJwtDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create (
            corruptionHandler = ReplaceFileCorruptionHandler (
                produceNewData = { emptyPreferences() }
            ),
            produceFile = { context.preferencesDataStoreFile(ONBOARDING_PREFERENCES) }
        )
    }

    // MARK: - Room db
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "app.dp"
        ).fallbackToDestructiveMigration().build()
    }

}