package com.example.pokedex.network.model

import com.google.gson.annotations.SerializedName

data class PokemonDetail(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("sprites")
    val sprites: Sprites,
    @SerializedName("types")
    val types: List<Types>
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

class Types(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: Type
)

class Type(
    @SerializedName("name")
    val name: String
)

