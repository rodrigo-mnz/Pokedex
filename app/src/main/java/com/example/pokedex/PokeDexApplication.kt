package com.example.pokedex

import android.app.Application
import com.example.pokedex.di.networkModule
import com.example.pokedex.di.repositoryModule
import com.example.pokedex.di.viewModelModule
import com.example.pokedex.network.DefaultLocalDataSource
import org.koin.core.context.GlobalContext.startKoin

class PokeDexApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        DefaultLocalDataSource.init(this)

        startKoin {
            modules(networkModule, viewModelModule, repositoryModule)
        }
    }
}