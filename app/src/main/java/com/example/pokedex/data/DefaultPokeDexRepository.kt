package com.example.pokedex.data

import com.example.pokedex.network.PokeDexService
import com.example.pokedex.network.model.PokemonList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single

class DefaultPokeDexRepository(
    private val pokeDexService: PokeDexService
) : PokeDexRepository {

    override fun fetchPokemonList(): Flow<Result<PokemonList>> {
        return flow {
            val result = Result.runCatching {
                pokeDexService.fetchPokemonList(20, 0)
            }
            emit(result)
        }
    }
}