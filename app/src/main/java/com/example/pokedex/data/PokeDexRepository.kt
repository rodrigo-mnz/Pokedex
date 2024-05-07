package com.example.pokedex.data

import com.example.pokedex.ui.model.PokemonListUIModel
import kotlinx.coroutines.flow.Flow

interface PokeDexRepository {

    fun fetchPokemonList(limit: Int, offset: Int): Flow<Result<PokemonListUIModel>>
}
