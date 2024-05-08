package com.example.pokedex.fakes

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokedex.ui.model.PokemonUIModel

class FakePagingSource : PagingSource<Int, PokemonUIModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonUIModel> {

        return LoadResult.Page(
            data = pokemonList,
            prevKey = null,
            nextKey = null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonUIModel>): Int? {
        return null
    }

    private val pokemonList = listOf(
        PokemonUIModel(
            id = 1,
            name = "Bulbasaur",
            image = "test",
            types = listOf("grass", "poison"),
            stats = emptyList(),
            cries = "",
            height = 0,
            weight = 0
        ),
    )
}