package org.train.projecto

import org.train.projecto.data.remote.dto.PokemonDto
import org.train.projecto.data.toPokemonPreview
import kotlin.test.Test
import kotlin.test.assertEquals
import org.train.projecto.data.remote.dto.SpritesDto

class PokemonMapperAndroidHostTest {

    @Test
    fun `preview mapping keeps a missing artwork URL as null`() {
        val preview = PokemonDto(
            id = 25,
            name = "pikachu",
            height = 4,
            weight = 60,
            types = emptyList(),
            abilities = emptyList(),
            stats = emptyList(),
            sprites = SpritesDto()
        ).toPokemonPreview()

        assertEquals(25, preview.id)
        assertEquals("pikachu", preview.name)
        assertEquals(null, preview.image)
    }
}

