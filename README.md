# Pocketdex

Pocketdex is a cross-platform Pokédex app for Android and iOS. It lets trainers browse Pokémon, search the local Pokédex, view detailed stats, and build a personal collection—all in a shared Kotlin Multiplatform codebase.

## Features

- Guided first-run onboarding with a trainer nickname
- One-time download of Pokémon records from [PokéAPI](https://pokeapi.co/)
- Fast, offline-friendly search backed by a local database
- Pokémon detail pages with artwork, height, weight, abilities, and base stats
- Recently viewed Pokémon on the home screen
- A personal collection that can be managed from Pokémon details
- Persistent profile and app preferences
- Light and dark themes
- English and Ukrainian interfaces
- Native Android and iOS entry points with a shared Compose UI

## Tech stack

- [Kotlin Multiplatform](https://www.jetbrains.com/kotlin-multiplatform/) for shared Android and iOS code
- [Compose Multiplatform](https://www.jetbrains.com/compose-multiplatform/) and Material 3 for the UI
- [Ktor](https://ktor.io/) for API networking
- [Room 3](https://developer.android.com/kotlin/multiplatform/room) with bundled SQLite for local Pokémon storage
- DataStore Preferences for settings and onboarding state
- [Koin](https://insert-koin.io/) for dependency injection
- Coil for image loading
- Kotlinx Serialization and Compose Navigation

## Project structure

```text
.
├── androidApp/       # Android launcher module and platform configuration
├── iosApp/           # Xcode project and SwiftUI launcher
├── shared/           # Shared UI, domain, data, and platform-specific KMP code
│   ├── src/commonMain/   # Compose UI, view models, repositories, API, persistence
│   ├── src/androidMain/  # Android database and storage implementations
│   └── src/iosMain/      # iOS database and storage implementations
└── gradle/           # Gradle wrapper and version catalog
```

## Getting started

### Prerequisites

- JDK 21
- Android Studio with an Android SDK and an emulator/device configured
- Xcode for building the iOS app (macOS only)

### Clone and open

```bash
git clone <your-repository-url>
cd Pockedex-App
```

Open the root directory in Android Studio. The project uses the included Gradle wrapper, so no separate Gradle installation is needed.

### Run on Android

Select the `androidApp` run configuration in Android Studio and run it on an emulator or connected device.

To create a debug APK from the terminal:

```bash
./gradlew :androidApp:assembleDebug
```

The APK is generated under `androidApp/build/outputs/apk/debug/`.

### Run on iOS

1. Open `iosApp/iosApp.xcodeproj` in Xcode.
2. Choose an iOS simulator or physical device.
3. Build and run the `iosApp` scheme.

The SwiftUI app hosts the shared Compose UI and initializes the shared dependency graph at startup.

## How it works

On the first launch, Pocketdex downloads up to 2,000 Pokémon previews from PokéAPI and stores them locally. Subsequent searching, collection management, and recently viewed lists use the local database; Pokémon details are requested when opened. Settings such as nickname, language, and theme are kept in DataStore.

An internet connection is required for the initial download and for loading Pokémon detail data and images.

## Tests

Run the Android host tests:

```bash
./gradlew :shared:testAndroidHostTest
```

Run the iOS simulator tests (with an iOS simulator environment available):

```bash
./gradlew :shared:iosSimulatorArm64Test
```

## Data source and attribution

Pokémon data and sprite URLs are provided by [PokéAPI](https://pokeapi.co/). Pokémon and Pokémon character names are trademarks of Nintendo, Game Freak, and Creatures Inc. This project is an unofficial, educational fan application and is not affiliated with or endorsed by those companies.

## License

This project is licensed under the [MIT License](LICENSE).

