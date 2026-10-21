package org.train.projecto.presentation.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed interface Route {

    @Serializable
    data object Login : Route

    @Serializable
    data object Download : Route

    @Serializable
    data object Main : Route

    @Serializable
    data object Search : Route

    @Serializable
    data object Settings : Route

    @Serializable
    data class PokemonDetails(
        val pokemonId: Int
    ) : Route

    @Serializable
    data object Collection : Route
}

