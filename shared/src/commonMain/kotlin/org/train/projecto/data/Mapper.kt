package org.train.projecto.data

import org.train.projecto.data.local.entities.PokemonPreviewEntity
import org.train.projecto.data.remote.dto.PokemonDto
import org.train.projecto.data.remote.dto.PokemonListItemDto
import org.train.projecto.domain.models.PokemonDetails
import org.train.projecto.domain.models.PokemonPreview
import org.train.projecto.domain.models.PokemonStat

fun PokemonDto.toDetails(): PokemonDetails{

    val types_s: List<String> = this.types.map { it.type.name}
    val abilities_s: List<String> = this.abilities.map { it.ability.name}
    val stats_s: List<PokemonStat> = this.stats.map { PokemonStat(it.stat.name, it.baseStat)}
    val sprite: String? = this.sprites.other?.officialArtwork?.frontDefault




    return PokemonDetails(
        id = this.id,
        name = this.name,
        heightM = this.height / 10.0,
        weightKg = this.weight / 10.0,
        types = types_s,
        abilities = abilities_s,
        stats = stats_s,
        sprites = sprite)
}

fun PokemonDto.toPokemonPreview(): PokemonPreview{
    return PokemonPreview(
        id = this.id,
        name = this.name,
        image = this.sprites.other?.officialArtwork?.frontDefault
    )
}

fun PokemonPreview.toEntity(): PokemonPreviewEntity{
    return PokemonPreviewEntity(
        id = this.id,
        name = this.name,
        image = this.image,

    )
}

fun PokemonPreviewEntity.toDomain(): PokemonPreview{
    return PokemonPreview(
        id = this.id,
        name = this.name,
        image = this.image
    )
}

fun PokemonListItemDto.toPokemonPreview(): PokemonPreview{
    val id=  this.url.trimEnd('/').substringAfterLast('/').toInt()
    return PokemonPreview(
        id = id,
        name = this.name,
        image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

    )
}