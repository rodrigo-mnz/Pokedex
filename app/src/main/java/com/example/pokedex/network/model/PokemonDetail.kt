package com.example.pokedex.network.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class PokemonDetail(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("sprites")
    val sprites: Sprites
)

data class Sprites(
    @SerializedName("other")
    val other: Other
)

data class Other(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork
)

class OfficialArtwork(
    @SerializedName("front_default")
    val frontDefault: String
)

