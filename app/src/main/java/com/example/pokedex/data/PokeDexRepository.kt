package com.example.pokedex.data

import com.example.pokedex.network.model.PokemonList
import kotlinx.coroutines.flow.Flow

interface PokeDexRepository {

    fun fetchPokemonList(): Flow<Result<PokemonList>>
}
