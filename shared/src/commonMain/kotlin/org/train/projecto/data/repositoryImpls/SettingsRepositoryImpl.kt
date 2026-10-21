package org.train.projecto.data.repositoryImpls

import kotlinx.coroutines.flow.Flow
import org.train.projecto.data.local.datastore.AppSettings
import org.train.projecto.data.local.datastore.SettingsDataStore
import org.train.projecto.domain.repositories.SettingsRepository

class SettingsRepositoryImpl(private val settingsDataStore: SettingsDataStore): SettingsRepository {
    override val settings: Flow<AppSettings> = settingsDataStore.settings
    //В repository я не буду добавляти get методи, бо типу можу отримати налаштування з об'єкта settings

    override suspend fun setNickname(nickname: String) {
        settingsDataStore.setNickname(nickname)
    }

    override suspend fun toggleDarkTheme() {
        settingsDataStore.toggleDarkTheme()
    }

    override suspend fun setLanguage(languageCode: String) {
        settingsDataStore.setLanguageCode(languageCode)
    }

    override suspend fun completeOnboarding() {
        settingsDataStore.setOnboardingCompleted()
    }

    override suspend fun completeDownloading() {
        settingsDataStore.setPokemonsDownloadedCompleted()
    }



}