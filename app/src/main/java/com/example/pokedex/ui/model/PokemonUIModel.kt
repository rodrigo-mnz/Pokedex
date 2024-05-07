package com.example.pokedex.ui.model

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.example.pokedex.network.model.PokemonDetail

data class PokemonUIModel(
    val id: Int,
    val name: String,
    val image: String
) {
    companion object {
        fun from(pokemon: PokemonDetail) = PokemonUIModel(
            id = pokemon.id,
            name = pokemon.name.capitalize(Locale.current),
            image = pokemon.sprites.other.officialArtwork.frontDefault
        )
    }
}