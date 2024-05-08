package com.example.pokedex.network.model

import com.google.gson.annotations.SerializedName

data class PokemonList(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: String,
    @SerializedName("results")
    val results: List<Pokemon>
)

data class Pokemon(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

fun Pokemon.extractId() =
    if (url.isEmpty() || !url.endsWith("/")) {
        throw IllegalArgumentException("Invalid URL: $url")
    } else {
        url.substring(0, url.length - 1).substringAfterLast("/").toInt()
    }
