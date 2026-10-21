package org.train.projecto

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import org.train.projecto.data.toDetails
import org.train.projecto.data.toDomain
import org.train.projecto.data.toEntity
import org.train.projecto.data.toPokemonPreview
import org.train.projecto.data.local.datastore.AppSettings
import org.train.projecto.data.local.entities.PokemonPreviewEntity
import org.train.projecto.data.remote.dto.AbilitySlotDto
import org.train.projecto.data.remote.dto.ArtworkDto
import org.train.projecto.data.remote.dto.NamedResourceDto
import org.train.projecto.data.remote.dto.OtherSpritesDto
import org.train.projecto.data.remote.dto.PokemonDto
import org.train.projecto.data.remote.dto.PokemonListItemDto
import org.train.projecto.data.remote.dto.SpritesDto
import org.train.projecto.data.remote.dto.StatSlotDto
import org.train.projecto.data.remote.dto.TypeSlotDto
import org.train.projecto.presentation.localization.stringsFor

class SharedCommonTest {

    @Test
    fun `pokemon dto maps its detail data to domain units`() {
        val dto = PokemonDto(
            id = 25,
            name = "pikachu",
            height = 4,
            weight = 60,
            types = listOf(TypeSlotDto(NamedResourceDto("electric"))),
            abilities = listOf(AbilitySlotDto(NamedResourceDto("static"))),
            stats = listOf(StatSlotDto(55, NamedResourceDto("speed"))),
            sprites = SpritesDto(OtherSpritesDto(ArtworkDto("https://example.com/pikachu.png")))
        )

        val details = dto.toDetails()

        assertEquals(25, details.id)
        assertEquals("pikachu", details.name)
        assertEquals(0.4, details.heightM)
        assertEquals(6.0, details.weightKg)
        assertEquals(listOf("electric"), details.types)
        assertEquals(listOf("static"), details.abilities)
        assertEquals("speed", details.stats.single().stat)
        assertEquals(55, details.stats.single().baseStat)
        assertEquals("https://example.com/pikachu.png", details.sprites)
    }

    @Test
    fun `preview conversion preserves local collection fields`() {
        val entity = PokemonPreviewEntity(
            id = 7,
            name = "squirtle",
            image = "https://example.com/squirtle.png",
            viewedAt = 42,
            isInCollection = true
        )

        val preview = entity.toDomain()
        val restoredEntity = preview.toEntity()

        assertEquals(7, preview.id)
        assertEquals("squirtle", preview.name)
        assertEquals("https://example.com/squirtle.png", preview.image)
        assertEquals(7, restoredEntity.id)
        assertEquals("squirtle", restoredEntity.name)
        assertEquals("https://example.com/squirtle.png", restoredEntity.image)
        assertEquals(0, restoredEntity.viewedAt)
        assertFalse(restoredEntity.isInCollection)
    }

    @Test
    fun `list item URL produces preview with Pokemon id and official artwork URL`() {
        val preview = PokemonListItemDto(
            name = "bulbasaur",
            url = "https://pokeapi.co/api/v2/pokemon/1/"
        ).toPokemonPreview()

        assertEquals(1, preview.id)
        assertEquals("bulbasaur", preview.name)
        assertEquals(
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
            preview.image
        )
    }

    @Test
    fun `localization falls back to English and defaults start onboarding incomplete`() {
        assertEquals("Search Pokémon", stringsFor("en").searchPokemon)
        assertEquals("Search Pokémon", stringsFor("unsupported").searchPokemon)
        assertEquals("Пошук покемонів", stringsFor("uk").searchPokemon)
        assertEquals("", AppSettings().nickname)
        assertFalse(AppSettings().isOnboardingCompleted)
        assertNull(PokemonDto(
            id = 1,
            name = "bulbasaur",
            height = 7,
            weight = 69,
            types = emptyList(),
            abilities = emptyList(),
            stats = emptyList(),
            sprites = SpritesDto()
        ).toPokemonPreview().image)
    }
}
