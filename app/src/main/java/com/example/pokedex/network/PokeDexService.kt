package com.example.pokedex.network

import com.example.pokedex.network.model.PokemonDetail
import com.example.pokedex.network.model.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeDexService {

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonList

    @GET("pokemon/{id}")
    suspend fun fetchPokemon(
        @Path("id") id: Int
    ): PokemonDetail
}
