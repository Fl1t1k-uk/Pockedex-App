package org.train.projecto.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.koin.compose.viewmodel.koinViewModel
import org.train.projecto.domain.models.PokemonDetails
import org.train.projecto.presentation.screens.collectionScreen.CollectionRoute
import org.train.projecto.presentation.screens.downloadScreen.DownloadRoute
import org.train.projecto.presentation.screens.loadingScreen.LoadingScreen
import org.train.projecto.presentation.screens.loginScreen.LoginRoute
import org.train.projecto.presentation.screens.mainScreen.MainRoute
import org.train.projecto.presentation.screens.pokemonDetails.PokemonDetailsRoute
import org.train.projecto.presentation.screens.searchScreen.SearchRoute
import org.train.projecto.presentation.screens.settingsScreen.SettingsRoute
import org.train.projecto.presentation.uiStates.AppStartUiState
import org.train.projecto.presentation.viewmodels.AppViewModel


@Composable
fun AppNavigation(
    viewModel: AppViewModel = koinViewModel()
) {
    val startState by viewModel.startState.collectAsState()

    when (val state = startState) {
        AppStartUiState.Loading -> {
            LoadingScreen()
        }

        AppStartUiState.Login -> {
            AppNavHost(startDestination = Route.Login)
        }

        AppStartUiState.Download -> {
            AppNavHost(startDestination = Route.Download)
        }

        AppStartUiState.Main -> {
            AppNavHost(startDestination = Route.Main)
        }
    }
}


@Composable
fun AppNavHost(startDestination: Route){


        val navController = rememberNavController()
    NavHost(
        navController,
        startDestination = startDestination
    ){


        composable<Route.Login> {
            LoginRoute(
                onLoginSuccess = {
                    navController.navigate(Route.Main){
                        popUpTo(Route.Login){
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<Route.Download> {
            DownloadRoute(onFinished =
                {navController.navigate(Route.Main){
                    popUpTo(Route.Download){
                        inclusive = true
                    }
                } })
        }

        composable<Route.Main>{
            MainRoute(
                onOpenPokemon = { id ->
                    navController.navigate(Route.PokemonDetails(id))
                },

                onOpenCollection = {
                    navController.navigate(Route.Collection)
                },

                onOpenPokedex = {
                    navController.navigate(Route.Search)
                },
                onOpenSettings = {
                    navController.navigate(Route.Settings)
                }
            )
        }

        composable<Route.Collection> {
            CollectionRoute(
                onBack = {
                    navController.popBackStack()
                },
                onOpenPokemon = { id ->
                    navController.navigate(Route.PokemonDetails(id))
                }
            )
        }

        composable<Route.Search>{
            SearchRoute(
                onBack = navController::popBackStack,
                onOpenPokemon = {id ->
                    navController.navigate(Route.PokemonDetails(id))
                }
            )
        }

        composable<Route.PokemonDetails> {backstackEntry ->
            val destination = backstackEntry.toRoute<Route.PokemonDetails>()
            PokemonDetailsRoute(
                pokemonId = destination.pokemonId,
                onBack = navController::popBackStack
            )
        }

        composable<Route.Settings> {
            SettingsRoute(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

    }

}