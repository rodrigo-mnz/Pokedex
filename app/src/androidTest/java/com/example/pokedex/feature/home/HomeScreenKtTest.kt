package com.example.pokedex.feature.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.pokedex.fakes.FakePagingSource
import com.example.pokedex.fakes.FakePokeDexRepository
import com.example.pokedex.ui.model.PokemonUIModel
import com.example.pokedex.ui.theme.PokedexTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val homeViewModel = HomeViewModel(FakePokeDexRepository(), FakePagingSource())

    @Test
    fun verifyHomeScreenIsDisplayedWithCorrectValues() {
        var clickedPokemon: PokemonUIModel? = null
        composeTestRule.setContent {
            PokedexTheme {
                HomeScreen(viewModel = homeViewModel) {
                    clickedPokemon = it
                }
            }
        }

        composeTestRule.onNodeWithText("Bulbasaur").assertIsDisplayed()
        composeTestRule.onNodeWithText("grass").assertIsDisplayed()
        composeTestRule.onNodeWithText("poison").assertIsDisplayed()
        composeTestRule.onNodeWithText("Bulbasaur").performClick()
        assertEquals(1, clickedPokemon?.id)
    }
}
