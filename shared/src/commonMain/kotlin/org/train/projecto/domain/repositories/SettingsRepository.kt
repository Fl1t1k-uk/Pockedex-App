package org.train.projecto.domain.repositories

import kotlinx.coroutines.flow.Flow
import org.train.projecto.data.local.datastore.AppSettings

interface SettingsRepository {
    val settings: Flow<AppSettings>

    suspend fun setNickname(nickname: String)
    suspend fun toggleDarkTheme()
    suspend fun setLanguage(languageCode: String)
    suspend fun completeOnboarding()

    suspend fun completeDownloading()


}