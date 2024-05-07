package com.example.pokedex.di

import com.example.pokedex.data.DefaultPokeDexRepository
import com.example.pokedex.data.PokeDexRepository
import com.example.pokedex.data.PokemonPagingSource
import com.example.pokedex.network.DefaultLocalDataSource
import com.example.pokedex.network.LocalDataSource
import com.example.pokedex.network.PokeDexService
import org.koin.dsl.module

fun provideRepository(
    service: PokeDexService,
    localDataSource: LocalDataSource
): PokeDexRepository = DefaultPokeDexRepository(service, localDataSource)

fun providePagingSource(
    repository: PokeDexRepository
): PokemonPagingSource = PokemonPagingSource(repository)

fun provideLocalDataSource() = DefaultLocalDataSource

val repositoryModule = module {
    single { provideRepository(get(), provideLocalDataSource()) }
    single { providePagingSource(get()) }
}