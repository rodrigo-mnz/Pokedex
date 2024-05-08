package com.example.pokedex.fakes

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokedex.ui.model.PokemonUIModel

class FakePagingSource : PagingSource<Int, PokemonUIModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonUIModel> {

        return LoadResult.Page(
            data = emptyList(),
            prevKey = null,
            nextKey = 0
        )
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonUIModel>): Int? {
        return null
    }
}