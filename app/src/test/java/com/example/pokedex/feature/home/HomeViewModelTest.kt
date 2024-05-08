package com.example.pokedex.feature.home

import androidx.paging.PagingSource
import com.example.pokedex.data.PokeDexRepository
import com.example.pokedex.fakes.FakePagingSource
import com.example.pokedex.fakes.FakePokeDexRepository
import com.example.pokedex.ui.model.PokemonUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private val repository: PokeDexRepository = FakePokeDexRepository()
    private val pagingSource: PagingSource<Int, PokemonUIModel> = FakePagingSource()

    private val viewModel = HomeViewModel(repository, pagingSource)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test1`() = runTest {
        // TODO: Figure out how to test a PagingData
        val test = viewModel.pokemonList.collectIndexed { index, value ->
            println(value)
        }
    }

}