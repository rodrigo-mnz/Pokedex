package com.example.pokedex.data

import com.example.pokedex.ui.model.PokemonUIModel
import com.example.pokedex.ui.model.PokemonListUIModel
import com.example.pokedex.network.LocalDataSource
import com.example.pokedex.network.PokeDexService
import com.example.pokedex.network.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultPokeDexRepository(
    private val pokeDexService: PokeDexService,
    private val localDataSource: LocalDataSource
) : PokeDexRepository {

    override fun fetchPokemonList(limit: Int, offset: Int): Flow<Result<PokemonListUIModel>> {
        return flow {
            val result = Result.runCatching {

                val localPokemonList = localDataSource.fetchPokemonList(limit, offset)
                val fetchPokemonList = if (localPokemonList != null) {
                    localPokemonList
                } else {
                    val fetchPokemonList = pokeDexService.fetchPokemonList(limit, offset)
                    localDataSource.savePokemonList(limit, offset, fetchPokemonList)
                    fetchPokemonList
                }

                fetchPokemonList
                    .results.map {
                        val pokemonId = it.extractId()
                        val localPokemon = localDataSource.fetchPokemon(pokemonId)
                        return@map if (localPokemon != null) {
                            localPokemon
                        } else {
                            val fetchPokemon = pokeDexService.fetchPokemon(pokemonId)
                            localDataSource.savePokemon(pokemonId, fetchPokemon)
                            fetchPokemon
                        }
                    }.map {
                        PokemonUIModel.from(it)
                    }
            }.map {
                PokemonListUIModel(it)
            }
            emit(result)
        }
    }
}

private fun Pokemon.extractId() = url.substring(0, url.length - 1).substringAfterLast("/").toInt()
