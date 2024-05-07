package com.example.pokedex.extensions

import androidx.compose.ui.graphics.Color

fun String.findColorFromType(): Color {
    //TODO: Map all type colors, improve this convertion
    return when (this) {
        "fire" -> com.example.pokedex.ui.theme.RedType
        "dark", "ghost", "poison", "psychic" -> com.example.pokedex.ui.theme.PurpleType
        "water", "ice" -> com.example.pokedex.ui.theme.BlueType
        "bug", "grass" -> com.example.pokedex.ui.theme.GreenType
        "electric" -> com.example.pokedex.ui.theme.YellowType
        else -> com.example.pokedex.ui.theme.LightGray
    }
}