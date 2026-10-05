package org.train.projecto.data.remote.dto

import kotlinx.serialization.Serializable

//Це для endpoint, коли ми повертаємо багато Pokemons. Знову ж там обгорток багато, тому і в нас так.
@Serializable
data class PokemonListDto(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<PokemonListItemDto>
)

@Serializable
data class PokemonListItemDto(
    val name: String,
    val url: String
)