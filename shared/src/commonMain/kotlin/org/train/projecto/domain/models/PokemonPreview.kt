package org.train.projecto.domain.models

data class PokemonPreview(
    val id: Int,
    val name: String,
    val image: String? = null,
)
