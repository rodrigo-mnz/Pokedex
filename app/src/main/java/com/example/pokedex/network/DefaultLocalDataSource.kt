package com.example.pokedex.network

import android.content.Context
import android.content.SharedPreferences
import com.example.pokedex.network.model.PokemonDetail
import com.example.pokedex.network.model.PokemonList
import com.google.gson.Gson


object DefaultLocalDataSource : LocalDataSource {

    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences("pokedex", Context.MODE_PRIVATE)
    }

    override fun fetchPokemonList(limit: Int, offset: Int): PokemonList? {
        val json = prefs.getString("$limit-$offset", null)
        return json?.let {
            Gson().fromJson(json, PokemonList::class.java)
        }
    }

    override fun savePokemonList(limit: Int, offset: Int, pokemonList: PokemonList) {
        prefs.edit().putString("$limit-$offset", Gson().toJson(pokemonList)).apply()
    }

    override fun fetchPokemon(id: Int): PokemonDetail? {
        val json = prefs.getString("$id", null)
        return json?.let {
            Gson().fromJson(json, PokemonDetail::class.java)
        }
    }

    override fun savePokemon(id: Int, pokemonDetail: PokemonDetail) {
        prefs.edit().putString("$id", Gson().toJson(pokemonDetail)).apply()
    }
}