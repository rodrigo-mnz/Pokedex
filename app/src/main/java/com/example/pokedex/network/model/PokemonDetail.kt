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
    val types: List<Types>,
    @SerializedName("stats")
    val stats: List<Stats>,
    @SerializedName("cries")
    val cries: Cries,
    @SerializedName("height")
    val height: Int,
    @SerializedName("weight")
    val weight: Int
)

data class Cries(
    @SerializedName("latest")
    val latest: String
)

data class Stats(
    @SerializedName("base_stat")
    val baseStat: Int,
    @SerializedName("stat")
    val stat: Stat
)

data class Stat(
    @SerializedName("name")
    val name: String
)

data class Sprites(
    @SerializedName("other")
    val other: Other
)

data class Other(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork
)

data class OfficialArtwork(
    @SerializedName("front_default")
    val frontDefault: String
)

data class Types(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: Type
)

data class Type(
    @SerializedName("name")
    val name: String
)
