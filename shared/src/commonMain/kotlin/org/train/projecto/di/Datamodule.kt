package org.train.projecto.di


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.koin.plugin.module.dsl.create
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single
import org.koin.plugin.module.dsl.viewModel
import org.train.projecto.data.repositoryImpls.PokemonRepositoryImpl
import org.train.projecto.data.local.AppDatabase
import org.train.projecto.data.local.PokemonPreviewDao
import org.train.projecto.data.local.createDatabase
import org.train.projecto.data.local.createDatastore
import org.train.projecto.data.local.createPokemonDao
import org.train.projecto.data.local.datastore.SettingsDataStore
import org.train.projecto.data.remote.PokemonApi
import org.train.projecto.data.remote.createHttpClient
import org.train.projecto.data.repositoryImpls.SettingsRepositoryImpl
import org.train.projecto.domain.repositories.PokemonRepository
import org.train.projecto.domain.repositories.SettingsRepository
import org.train.projecto.presentation.viewmodels.AppViewModel
import org.train.projecto.presentation.viewmodels.CollectionViewModel
import org.train.projecto.presentation.viewmodels.DownloadViewModel
import org.train.projecto.presentation.viewmodels.LoginViewModel
import org.train.projecto.presentation.viewmodels.MainViewModel
import org.train.projecto.presentation.viewmodels.PokemonDetailsViewModel
import org.train.projecto.presentation.viewmodels.SearchViewModel
import org.train.projecto.presentation.viewmodels.SettingsViewModel

val dataModule = module {

        single<AppDatabase> {
            create(::createDatabase)
        }
        single<PokemonPreviewDao> {
            create(::createPokemonDao)
        }
        single<PokemonApi>()

        single<SettingsDataStore>()

        single<DataStore<Preferences>>{
        create(::createDatastore)
        }

        single<PokemonRepositoryImpl>() bind PokemonRepository::class

        single<SettingsRepositoryImpl>() bind SettingsRepository::class

        single<HttpClient> {
            create(::createHttpClient)
        }
        viewModel<LoginViewModel>()
        viewModel<MainViewModel>()
        viewModel<SearchViewModel>()
        viewModel<SettingsViewModel>()
        viewModel<AppViewModel>()
        viewModel<DownloadViewModel>()
        viewModel<CollectionViewModel>()
    viewModel { parameters -> // Це, тому що DetailsViewModel треба параметр private val id: Int
        PokemonDetailsViewModel(
            id = parameters.get(),
            pokemonRepository = get()
        )
    }
    }






