package com.example.pokedex.ui.model

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.example.pokedex.network.model.PokemonDetail

data class PokemonUIModel(
    val id: Int,
    val name: String,
    val image: String,
    val types: List<String>,
    val stats: List<Pair<String, Int>>,
    val cries: String,
    val height: Int,
    val weight: Int
) {
    companion object {
        fun from(pokemon: PokemonDetail) = PokemonUIModel(
            id = pokemon.id,
            name = pokemon.name.capitalize(Locale.current),
            image = pokemon.sprites.other.officialArtwork.frontDefault,
            types = pokemon.types.map { it.type.name },
            stats = pokemon.stats.map { Pair(it.stat.name, it.baseStat) },
            cries = pokemon.cries.latest,
            height = pokemon.height,
            weight = pokemon.weight
        )
    }
}