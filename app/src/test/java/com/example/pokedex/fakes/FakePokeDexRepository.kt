package com.example.pokedex.fakes

import com.example.pokedex.data.PokeDexRepository
import com.example.pokedex.ui.model.PokemonListUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePokeDexRepository : PokeDexRepository {

    override fun fetchPokemonList(limit: Int, offset: Int): Flow<Result<PokemonListUIModel>> {
        return flow {

        }
    }
}
