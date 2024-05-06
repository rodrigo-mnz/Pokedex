package com.example.pokedex.di

import com.example.pokedex.data.DefaultPokeDexRepository
import com.example.pokedex.data.PokeDexRepository
import com.example.pokedex.home.HomeViewModel
import com.example.pokedex.network.PokeDexService
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.get

fun provideRepository(service: PokeDexService): PokeDexRepository = DefaultPokeDexRepository(service)

val repositoryModule = module {
    single { provideRepository(get()) }
}