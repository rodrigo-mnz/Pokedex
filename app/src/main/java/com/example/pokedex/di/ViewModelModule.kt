package com.example.pokedex.di

import com.example.pokedex.data.PokeDexRepository
import com.example.pokedex.home.HomeViewModel
import org.koin.dsl.module

fun provideHomeViewModel(repository: PokeDexRepository) = HomeViewModel(repository)

val viewModelModule = module {
    single { provideHomeViewModel(get()) }
}