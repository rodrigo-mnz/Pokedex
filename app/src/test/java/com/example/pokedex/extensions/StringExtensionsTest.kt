package com.example.pokedex.extensions

import com.example.pokedex.ui.theme.BlueType
import com.example.pokedex.ui.theme.GreenType
import com.example.pokedex.ui.theme.LightGray
import com.example.pokedex.ui.theme.PurpleType
import com.example.pokedex.ui.theme.RedType
import com.example.pokedex.ui.theme.YellowType
import org.junit.Assert.assertEquals
import org.junit.Test

class StringExtensionsTest {

    @Test
    fun `verify findColorFromType returns correct color for each mapped type`() {
        val color1 = "fire".findColorFromType()
        val color2 = "dark".findColorFromType()
        val color3 = "water".findColorFromType()
        val color4 = "bug".findColorFromType()
        val color5 = "electric".findColorFromType()

        assertEquals(RedType, color1)
        assertEquals(PurpleType, color2)
        assertEquals(BlueType, color3)
        assertEquals(GreenType, color4)
        assertEquals(YellowType, color5)
    }

    @Test
    fun `verify findColorFromType returns default color for unknown type`() {
        val color = "pikachu".findColorFromType()

        assertEquals(LightGray, color)
    }

    @Test
    fun `verify findColorFromType returns correct color for grouped types`() {
        val color1 = "ghost".findColorFromType()
        val color2 = "dark".findColorFromType()
        val color3 = "poison".findColorFromType()
        val color4 = "psychic".findColorFromType()
        val color5 = "water".findColorFromType()
        val color6 = "ice".findColorFromType()
        val color7 = "bug".findColorFromType()
        val color8 = "grass".findColorFromType()

        assertEquals(PurpleType, color1)
        assertEquals(PurpleType, color2)
        assertEquals(PurpleType, color3)
        assertEquals(PurpleType, color4)

        assertEquals(BlueType, color5)
        assertEquals(BlueType, color6)

        assertEquals(GreenType, color7)
        assertEquals(GreenType, color8)
    }
}
