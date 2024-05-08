package com.example.pokedex.di

import androidx.paging.PagingSource
import com.example.pokedex.data.PokeDexRepository
import com.example.pokedex.data.PokeDexRepositoryImpl
import com.example.pokedex.data.PokemonPagingSource
import com.example.pokedex.network.LocalDataSource
import com.example.pokedex.network.LocalDataSourceImpl
import com.example.pokedex.network.PokeDexService
import com.example.pokedex.ui.model.PokemonUIModel
import org.koin.dsl.module

fun provideRepository(
    service: PokeDexService,
    localDataSource: LocalDataSource
): PokeDexRepository = PokeDexRepositoryImpl(service, localDataSource)

fun providePagingSource(
    repository: PokeDexRepository
): PagingSource<Int, PokemonUIModel> = PokemonPagingSource(repository)

fun provideLocalDataSource() = LocalDataSourceImpl

val repositoryModule = module {
    single { provideRepository(get(), provideLocalDataSource()) }
    single { providePagingSource(get()) }
}