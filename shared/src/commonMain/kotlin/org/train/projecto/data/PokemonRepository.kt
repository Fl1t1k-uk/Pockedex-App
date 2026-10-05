package org.train.projecto.data

import kotlinx.coroutines.flow.Flow
import org.train.projecto.data.local.PokemonPreviewDao
import org.train.projecto.data.remote.PokemonApi
import org.train.projecto.domain.models.PokemonDetails
import org.train.projecto.domain.models.PokemonPreview
import org.train.projecto.domain.models.repositories.PokemonRepository

//Реалізувати отримання кількох покемонів та потім через mappind ListItemDto отримати вже хвичайний PokemonPreview

class PokemonRepository(private val pokemonApi: PokemonApi, private val pokemonPreviewDao: PokemonPreviewDao ): PokemonRepository {
    override suspend fun getPokemonDetails(id: Int): PokemonDetails {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonPreview(id: Int): PokemonPreview {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonPreviews(
        limit: Int,
        offset: Int
    ): List<PokemonPreview> {
        TODO("Not yet implemented")
    }

    override suspend fun getLastFivePreviews(
        limit: Int,
        offset: Int
    ): Flow<List<PokemonPreview>> {
        TODO("Not yet implemented")
    }
}