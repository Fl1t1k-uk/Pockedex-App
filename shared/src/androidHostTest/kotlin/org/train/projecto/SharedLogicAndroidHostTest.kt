package org.train.projecto

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.train.projecto.data.remote.PokemonApi
import org.train.projecto.data.remote.dto.ArtworkDto
import org.train.projecto.data.remote.dto.OtherSpritesDto
import org.train.projecto.data.remote.dto.PokemonDto
import org.train.projecto.data.remote.dto.SpritesDto
import org.train.projecto.data.toPokemonPreview
import kotlin.test.Test
import kotlin.test.assertEquals


class PokemonMapperTest {

    @Test
    fun `toPokemonPreview should map image correctly`() {
        // Arrange
        val imageUrl = "https://example.com/pikachu.png"

        val dto = PokemonDto(
            id = 25,
            name = "pikachu",
            height = 4,
            weight = 60,
            types = emptyList(),
            abilities = emptyList(),
            stats = emptyList(),
            sprites = SpritesDto(
                other = OtherSpritesDto(
                    officialArtwork = ArtworkDto(
                        frontDefault = imageUrl
                    )
                )
            )
        )

        // Act
        val preview = dto.toPokemonPreview()

        // Assert
        assertNotNull(preview.image)
        assertEquals(imageUrl, preview.image)
    }
}

class ApiTest{
private val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
            }
        )
    }

    defaultRequest {
        url("https://pokeapi.co/api/v2/")
    }
}

private val pokemonApi = PokemonApi(client)

    @Test
    fun `pokemon api should return image url`() = runTest{
        val pokemon = pokemonApi.getPokemonById(25)

    assertNotNull(pokemon)
    assertNotNull(pokemon.sprites.other?.officialArtwork?.frontDefault)
}
}

