package org.train.projecto.domain.models.repositories

import kotlinx.coroutines.flow.Flow
import org.train.projecto.data.remote.dto.PokemonDto
import org.train.projecto.data.remote.dto.PokemonListDto
import org.train.projecto.domain.models.PokemonDetails
import org.train.projecto.domain.models.PokemonPreview

interface PokemonRepository {
    suspend fun getPokemonDetails(id: Int): PokemonDetails

    suspend fun getPokemonPreview(id: Int): PokemonPreview

    suspend fun getPokemonPreviews(limit: Int = 20, offset: Int = 0): List<PokemonPreview>

    suspend fun getLastFivePreviews(limit: Int = 20, offset: Int = 0): Flow<List<PokemonPreview>>
}