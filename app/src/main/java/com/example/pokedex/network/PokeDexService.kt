package com.example.pokedex.network

import com.example.pokedex.network.model.PokemonList
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeDexService {

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): PokemonList
}