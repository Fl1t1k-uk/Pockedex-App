package org.train.projecto.data.local

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import androidx.room3.Upsert
import kotlinx.coroutines.flow.Flow
import org.train.projecto.data.local.entities.PokemonPreviewEntity

@Dao
interface PokemonPreviewDao {

    @Query("""
        SELECT * FROM pokemon_previews 
        ORDER BY viewedAt DESC
        LIMIT :limit OFFSET :offset
    """)
    fun getPokemonPreviewsForPeriod(
        limit: Int,
        offset: Int
    ): Flow<List<PokemonPreviewEntity>>

    @Query("Select * From pokemon_previews WHERE id = :id")
    fun getPokemonById(id: Int): Flow<PokemonPreviewEntity>

    @Query("SELECT * FROM pokemon_previews WHERE isInCollection = 1")
    fun getPokemonsInCollection(): Flow<List<PokemonPreviewEntity>>


    @Query("SELECT COUNT(*) FROM pokemon_previews WHERE isInCollection = 1")
    fun getCollectionCount(): Flow<Int>
    @Query("UPDATE pokemon_previews SET viewedAt = :time WHERE id = :id")
    suspend fun updateViewedAt(id: Int, time: Long)

    @Query("UPDATE pokemon_previews SET isInCollection = NOT isInCollection WHERE id = :id")
    suspend fun toggleCollection(id: Int)

    @Query("UPDATE pokemon_previews SET  image = :value WHERE id = :id")
    suspend fun updateImage(id: Int, value: String?)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(pokemons: List<PokemonPreviewEntity>)


    @Query("""
        SELECT * FROM pokemon_previews 
        WHERE name LIKE '%' || :query || '%'
        ORDER BY name ASC
        LIMIT :limit OFFSET :offset
    """)
    fun getPokemonPreviewsByQuery(
        query: String,
        limit: Int,
        offset: Int
    ): Flow<List<PokemonPreviewEntity>>
}