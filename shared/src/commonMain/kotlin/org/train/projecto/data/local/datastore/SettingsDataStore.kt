package org.train.projecto.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsDataStore(private val dataStore: DataStore<Preferences>){

    val settings: Flow<AppSettings> = dataStore.data.map { preferences ->
        AppSettings(
            nickname = preferences[Keys.nickname] ?: "",
            isDarkTheme = preferences[Keys.darkTheme] ?: false,
            languageCode = preferences[Keys.languageCode] ?: "ua",
            isOnboardingCompleted = preferences[Keys.onboardingComplete] ?: false,
            isPokemonsDownloaded = preferences[Keys.pokemonsDownloaded] ?: false
            )
    }




    suspend fun setNickname(nickname: String) {
        dataStore.edit { preferences ->
            preferences[Keys.nickname] = nickname
        }
    }

    suspend fun toggleDarkTheme(){
        dataStore.edit { preferences ->
            val value = preferences[Keys.darkTheme] ?: false
            preferences[Keys.darkTheme] = !value
        }
    }

    suspend fun setLanguageCode(languageCode: String){
        dataStore.edit { preferences ->
            preferences[Keys.languageCode] = languageCode
        }
    }

    suspend fun setOnboardingCompleted(){
        dataStore.edit { preferences ->
            preferences[Keys.onboardingComplete] = true
        }
    }

    suspend fun setPokemonsDownloadedCompleted(){
        dataStore.edit { preferences ->
            preferences[Keys.pokemonsDownloaded] = true
        }
    }

    private object Keys{
        val nickname = stringPreferencesKey("nickname")
        val darkTheme = booleanPreferencesKey("dark_theme")
        val languageCode = stringPreferencesKey("language_code")
        val onboardingComplete = booleanPreferencesKey("onboarding_completed")

        val pokemonsDownloaded = booleanPreferencesKey("pokemons_downloaded")
    }


}