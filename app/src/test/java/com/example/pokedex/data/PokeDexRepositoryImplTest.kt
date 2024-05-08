package com.example.pokedex.data

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.example.pokedex.network.LocalDataSource
import com.example.pokedex.network.PokeDexService
import com.example.pokedex.network.model.Cries
import com.example.pokedex.network.model.OfficialArtwork
import com.example.pokedex.network.model.Other
import com.example.pokedex.network.model.Pokemon
import com.example.pokedex.network.model.PokemonDetail
import com.example.pokedex.network.model.PokemonList
import com.example.pokedex.network.model.Sprites
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.wheneverBlocking


class PokeDexRepositoryImplTest {

    private val pokeDexService = mock(PokeDexService::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)
    private val repository = PokeDexRepositoryImpl(pokeDexService, localDataSource)

    @Test
    fun `verify fetchPokemonList empty behaviour`() {
        runTest {
            wheneverBlocking { pokeDexService.fetchPokemonList(0, 0) } doReturn emptyList

            repository.fetchPokemonList(0, 0).collectLatest {
                assert(it.getOrThrow().list.isEmpty())
            }
        }
    }

    @Test
    fun `verify fetchPokemonList mapping correct values`() {
        runTest {
            wheneverBlocking {
                pokeDexService.fetchPokemonList(
                    anyInt(),
                    anyInt()
                )
            } doReturn pokemonList
            wheneverBlocking {
                pokeDexService.fetchPokemon(anyInt())
            } doReturn pokemonDetail

            repository.fetchPokemonList(0, 0).collectLatest {
                val first = it.getOrThrow().list.first()
                assertEquals(pokemonDetail.name.capitalize(Locale.current), first.name)
                assertEquals(pokemonDetail.id, first.id)
                assertEquals(pokemonDetail.height, first.height)
            }
        }
    }

    private val pokemonDetail = PokemonDetail(
        id = 1,
        name = "bulbasaur",
        height = 7,
        weight = 69,
        types = emptyList(),
        stats = emptyList(),
        sprites = Sprites(
            Other(
                OfficialArtwork("")
            )
        ),
        cries = Cries("")
    )

    private val emptyList = PokemonList(
        count = 0,
        next = "",
        previous = "",
        results = emptyList()
    )

    private val pokemonList = PokemonList(
        count = 0,
        next = "",
        previous = "",
        results = listOf(
            Pokemon("1", "url/1/"),
            Pokemon("2", "url/2/"),
            Pokemon("3", "url/3/")
        )
    )
}