package org.train.projecto.data.repositoryImpls

import androidx.sqlite.SQLiteException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.io.IOException
import org.train.projecto.data.local.PokemonPreviewDao
import org.train.projecto.data.local.entities.PokemonPreviewEntity
import org.train.projecto.data.remote.PokemonApi
import org.train.projecto.data.toDetails
import org.train.projecto.data.toDomain
import org.train.projecto.domain.models.PokemonDetails
import org.train.projecto.domain.models.PokemonPreview
import org.train.projecto.domain.repositories.PokemonRepository

class PokemonRepositoryImpl(private val pokemonApi: PokemonApi, private val pokemonPreviewDao: PokemonPreviewDao):
    PokemonRepository {

    override suspend fun downloadAllPokemons(): Result<Unit> {
        return try{
            val pokemonListItemDtos = pokemonApi.getPokemons(limit = 2000, offset = 0).results
            pokemonPreviewDao.insertAll(
                pokemonListItemDtos.map {
                    val id = it.url.trimEnd('/')
                        .substringAfterLast('/')
                        .toInt()

                    val image =
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"

                    PokemonPreviewEntity(
                        id = id,
                        name = it.name,
                        image = image
                    )
                }
            )

            Result.success(Unit)
        }
        catch (e: CancellationException){
            throw e
        }

        catch (e: SQLiteException){
            Result.failure(e)
        }

        catch (e: IOException){
            Result.failure(e)
        }


    }





    override suspend fun getPokemonDetails(id: Int): Result<PokemonDetails> {
        return try {
            val pokemon = pokemonApi.getPokemonById(id).toDetails()
            Result.success(pokemon)
        }
        catch (e: CancellationException){
            throw e
        }
        catch (e: Exception){
            Result.failure(e)
        }
    }

    override fun getPokemonsInCollection(): Flow<List<PokemonPreview>> {
        return pokemonPreviewDao.getPokemonsInCollection().map { pokemons -> pokemons.map { it.toDomain() } }
    }

    override fun checkIsInCollection(id: Int): Flow<Boolean> {
        return pokemonPreviewDao.getPokemonById(id).map { it.isInCollection }
    }


    override fun getPokemonPreviewsByQuery(query: String, limit: Int, offset: Int): Flow<List<PokemonPreview>> {
        return pokemonPreviewDao.getPokemonPreviewsByQuery(query, limit, offset).map { previews -> previews.map { it.toDomain()}}
    }

     override fun getPokemonPreviewsByTime(limit: Int, offset: Int ): Flow<List<PokemonPreview>> {
         return pokemonPreviewDao.getPokemonPreviewsForPeriod(limit = limit, offset = offset).map { pokemons -> pokemons.map { it.toDomain() } }
     }

    override suspend fun updateViewedAt(viewedAt: Long, id: Int): Result<Unit> {
        return try{
            pokemonPreviewDao.updateViewedAt(id, viewedAt)
            Result.success(Unit)
        }
        catch (e: CancellationException){
            throw e
        }

        catch (e: SQLiteException){
            Result.failure(e)
        }

    }

    override suspend fun updateInCollectionStatus(
        id: Int
    ): Result<Unit> {
        return try{
            pokemonPreviewDao.toggleCollection(id)
            Result.success(Unit)
        }
        catch (e: CancellationException){
            throw e
        }
        catch (e: SQLiteException){
            Result.failure(e)
        }
    }

    override fun getCollectionCount(): Flow<Int> {
        return pokemonPreviewDao.getCollectionCount()

    }

    override suspend fun updateImage(
        id: Int,
        image: String?
    ): Result<Unit> {
        return try{
            pokemonPreviewDao.updateImage(id, image)
            Result.success(Unit)
        }
        catch (e: CancellationException){
            throw e
        }catch (e: SQLiteException){
            Result.failure(e)
        }
    }

}