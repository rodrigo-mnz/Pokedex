package com.example.pokedex.data

import com.example.pokedex.network.LocalDataSource
import com.example.pokedex.network.PokeDexService
import com.example.pokedex.network.model.Pokemon
import com.example.pokedex.network.model.PokemonDetail
import com.example.pokedex.network.model.PokemonList
import com.example.pokedex.network.model.extractId
import com.example.pokedex.ui.model.PokemonListUIModel
import com.example.pokedex.ui.model.PokemonUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokeDexRepositoryImpl(
    private val pokeDexService: PokeDexService,
    private val localDataSource: LocalDataSource
) : PokeDexRepository {

    override fun fetchPokemonList(limit: Int, offset: Int): Flow<Result<PokemonListUIModel>> {
        return flow {
            val result = Result.runCatching {

                val fetchPokemonList = pokemonList(limit, offset)

                fetchPokemonList.results.map {
                    return@map fetchPokemonDetail(it)
                }.map {
                    PokemonUIModel.from(it)
                }
            }.map {
                PokemonListUIModel(it)
            }
            emit(result)
        }
    }

    private suspend fun pokemonList(limit: Int, offset: Int): PokemonList {
        val localPokemonList = localDataSource.fetchPokemonList(limit, offset)
        val fetchPokemonList = if (localPokemonList != null) {
            localPokemonList
        } else {
            val fetchPokemonList = pokeDexService.fetchPokemonList(limit, offset)
            localDataSource.savePokemonList(limit, offset, fetchPokemonList)
            fetchPokemonList
        }
        return fetchPokemonList
    }

    private suspend fun fetchPokemonDetail(it: Pokemon): PokemonDetail {
        val pokemonId = it.extractId()
        val localPokemon = localDataSource.fetchPokemon(pokemonId)
        return if (localPokemon != null) {
            localPokemon
        } else {
            val fetchPokemon = pokeDexService.fetchPokemon(pokemonId)
            localDataSource.savePokemon(pokemonId, fetchPokemon)
            fetchPokemon
        }
    }
}
