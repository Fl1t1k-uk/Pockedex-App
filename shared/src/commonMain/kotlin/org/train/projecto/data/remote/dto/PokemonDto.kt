package org.train.projecto.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDto(
    val id: Int,
    val name: String,
    val height: Int, //В дециметрах
    val weight: Int, // kg * 10
    val types: List<TypeSlotDto>,
    val abilities: List<AbilitySlotDto>,
    val stats: List<StatSlotDto>,
    val sprites: SpritesDto
)



//В PokeApi багато вкладеності і найнижчим рівнем є {name: "", url: ""}, але нам не потрібне url, бо воно просто буде перекидувати на інформацію, яка нам не потібна.
@Serializable
data class NamedResourceDto(
    val name: String
)


@Serializable
data class TypeSlotDto(
    val type: NamedResourceDto
)

@Serializable
data class AbilitySlotDto(
    val ability: NamedResourceDto //Назва ability
)

@Serializable
data class StatSlotDto(
    @SerialName("base_stat") val baseStat: Int,
    val stat: NamedResourceDto //тут це буди виступати назвою характеристика
)


// Ця частина відповідає за картинку.
@Serializable
data class SpritesDto(
    val other: OtherSpritesDto? = null
)

@Serializable
data class OtherSpritesDto(
    @SerialName("official-artwork") val officialArtwork: ArtworkDto? = null
)

@Serializable
data class ArtworkDto(
    @SerialName("front_default") val frontDefault: String? = null
)