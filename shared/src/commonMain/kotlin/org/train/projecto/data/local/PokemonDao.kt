package org.train.projecto.data.local

import androidx.room3.Dao
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow
import org.train.projecto.data.local.entities.PokemonPreviewEntity

@Dao
interface PokemonPreviewDao {

    @Query("""
        SELECT * FROM pokemon_previews p
        WHERE viewedAt >= :since
        ORDER BY p.viewedAt DESC
    """)
    fun getPokemonPreviewsForPeriod(since: Long): Flow<List<PokemonPreviewEntity>>

    @Query("""
        SELECT * FROM pokemon_previews p
        WHERE p.name LIKE '%' || :query || '%'
        ORDER BY name ASC
    """)
    fun getPokemonPreviewsByQuery(query: String): Flow<List<PokemonPreviewEntity>>
}