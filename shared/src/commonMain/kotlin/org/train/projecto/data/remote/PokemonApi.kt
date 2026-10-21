package org.train.projecto.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.train.projecto.data.remote.dto.PokemonDto
import org.train.projecto.data.remote.dto.PokemonListDto

class PokemonApi(private val client: HttpClient) {

    suspend fun getPokemons(limit: Int = 20, offset: Int = 0): PokemonListDto {
        return client.get("pokemon"){
            parameter("limit", limit)
            parameter("offset", offset)
        }.body()

    }

    suspend fun getPokemonById(id: Int): PokemonDto {
        return client.get("pokemon/$id").body()
    }

    suspend fun getPokemonByName(query: String): PokemonDto{
        return client.get("pokemon/$query").body()
    }
}