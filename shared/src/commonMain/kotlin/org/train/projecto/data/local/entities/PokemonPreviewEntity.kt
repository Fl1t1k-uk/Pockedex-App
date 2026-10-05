package org.train.projecto.data.local.entities

import androidx.room3.Entity
import androidx.room3.PrimaryKey

// При запитах став strategy Ignore, а не Replace через те, що поставив defaults.

@Entity("pokemon_previews")
data class PokemonPreviewEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val image: String? = null,
    val viewedAt: Long = 0,
    val isInCollection: Boolean = false
)