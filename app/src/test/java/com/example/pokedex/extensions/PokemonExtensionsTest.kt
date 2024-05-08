package com.example.pokedex.extensions

import com.example.pokedex.network.model.Pokemon
import com.example.pokedex.network.model.extractId
import org.junit.Assert.assertEquals
import org.junit.Test

class PokemonExtensionsTest {

    @Test(expected = IllegalArgumentException::class)
    fun `verify extractId will throw exception when url format is wrong`() {
        val pokemon = Pokemon(
            name = "Test",
            url = "testing/1"
        )

        pokemon.extractId()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `verify extractId will throw exception when url is empty`() {
        val pokemon = Pokemon(
            name = "Test",
            url = ""
        )

        pokemon.extractId()
    }

    @Test
    fun `verify extractId will return correct id when url format is correct`() {
        val pokemon = Pokemon(
            name = "Test",
            url = "testing/1/"
        )

        val extractId = pokemon.extractId()

        assertEquals(1, extractId)
    }
}