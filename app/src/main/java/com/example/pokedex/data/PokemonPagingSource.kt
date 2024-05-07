package com.example.pokedex.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokedex.ui.model.PokemonListUIModel
import com.example.pokedex.ui.model.PokemonUIModel
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.single

class PokemonPagingSource(private val pokeDexRepository: PokeDexRepository) :
    PagingSource<Int, PokemonUIModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonUIModel> {
        val position = params.key ?: 0
        val offset = if (params.key != null) position else 0

        val data = pokeDexRepository.fetchPokemonList(offset = offset, limit = params.loadSize)

        val nextKey =
            if (data.last().getOrDefault(PokemonListUIModel(emptyList())).list.isEmpty()) {
                null
            } else {
                position + (params.loadSize)
            }

        return LoadResult.Page(
            data = data.single().getOrThrow().list,
            prevKey = null, // Only paging forward
            nextKey = nextKey
        )
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonUIModel>): Int? {
        return null
    }
}