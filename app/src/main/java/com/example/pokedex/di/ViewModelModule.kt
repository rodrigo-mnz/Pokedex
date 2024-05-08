package com.example.pokedex.di

import com.example.pokedex.data.PokeDexRepository
import com.example.pokedex.data.PokemonPagingSource
import com.example.pokedex.feature.home.HomeViewModel
import org.koin.dsl.module

fun provideHomeViewModel(repository: PokeDexRepository, pagingSource: PokemonPagingSource) =
    HomeViewModel(repository, pagingSource)

val viewModelModule = module {
    single { provideHomeViewModel(get(), get()) }
}