package org.train.projecto.domain.models

import kotlinx.serialization.SerialName
import org.train.projecto.data.remote.dto.AbilitySlotDto
import org.train.projecto.data.remote.dto.SpritesDto
import org.train.projecto.data.remote.dto.StatSlotDto
import org.train.projecto.data.remote.dto.TypeSlotDto

data class PokemonDetails(
    val id: Int,
    val name: String,
    val heightM: Double,
    val weightKg: Double,
    val types: List<String>,
    val abilities: List<String>,
    val stats: List<PokemonStat>,
    val sprites: String? = null

)

data class PokemonStat(
    val stat: String,
    val baseStat: Int,
)