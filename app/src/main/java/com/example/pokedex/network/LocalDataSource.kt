package com.example.pokedex.network

import com.example.pokedex.network.model.PokemonDetail
import com.example.pokedex.network.model.PokemonList

interface LocalDataSource {

    fun fetchPokemonList(
        limit: Int,
        offset: Int
    ): PokemonList?

    fun savePokemonList(
        limit: Int,
        offset: Int,
        pokemonList: PokemonList
    )

    fun fetchPokemon(id: Int): PokemonDetail?

    fun savePokemon(
        id: Int,
        pokemonDetail: PokemonDetail
    )
}