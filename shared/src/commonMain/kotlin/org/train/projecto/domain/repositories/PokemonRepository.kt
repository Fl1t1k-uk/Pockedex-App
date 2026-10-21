package org.train.projecto.domain.repositories

import kotlinx.coroutines.flow.Flow
import org.train.projecto.data.local.entities.PokemonPreviewEntity
import org.train.projecto.data.remote.dto.PokemonDto
import org.train.projecto.data.remote.dto.PokemonListDto
import org.train.projecto.domain.models.PokemonDetails
import org.train.projecto.domain.models.PokemonPreview

interface PokemonRepository {
    suspend fun getPokemonDetails(id: Int): Result<PokemonDetails>

    fun getPokemonsInCollection(): Flow<List<PokemonPreview>>

    fun checkIsInCollection(id: Int): Flow<Boolean>
    suspend fun downloadAllPokemons(): Result<Unit>
    fun getPokemonPreviewsByQuery(query: String, limit: Int = 20, offset: Int = 0): Flow<List<PokemonPreview>>

    fun getPokemonPreviewsByTime(limit: Int = 3, offset: Int = 0): Flow<List<PokemonPreview>>

    suspend fun updateViewedAt(viewedAt: Long, id: Int): Result<Unit>

    suspend fun updateInCollectionStatus(id: Int): Result<Unit>
    //Подумай, як тобі загружати в базу даних ці PokemonPreviews та як оновлювати в них ViewdAt

    fun getCollectionCount(): Flow<Int>
    suspend fun updateImage(id: Int, image: String?): Result<Unit>
}
