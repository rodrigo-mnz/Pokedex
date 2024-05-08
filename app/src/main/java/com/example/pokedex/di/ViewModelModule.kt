package com.example.pokedex.di

import androidx.paging.PagingSource
import com.example.pokedex.data.PokeDexRepository
import com.example.pokedex.feature.home.HomeViewModel
import com.example.pokedex.ui.model.PokemonUIModel
import org.koin.dsl.module

fun provideHomeViewModel(
    repository: PokeDexRepository,
    pagingSource: PagingSource<Int, PokemonUIModel>
) =
    HomeViewModel(repository, pagingSource)

val viewModelModule = module {
    single { provideHomeViewModel(get(), get()) }
}