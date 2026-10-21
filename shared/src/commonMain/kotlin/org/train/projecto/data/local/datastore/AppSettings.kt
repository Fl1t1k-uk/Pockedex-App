package org.train.projecto.data.local.datastore

data class AppSettings(
    val nickname: String = "",
    val isDarkTheme: Boolean = false,
    val languageCode: String = "uk",
    val isOnboardingCompleted: Boolean = false,
    val isPokemonsDownloaded: Boolean = false
    )