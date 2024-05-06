package com.example.pokedex

import android.app.Application
import com.example.pokedex.di.networkModule
import com.example.pokedex.di.repositoryModule
import com.example.pokedex.di.viewModelModule
import org.koin.core.context.GlobalContext.startKoin

class PokeDexApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(networkModule, viewModelModule, repositoryModule)
        }
    }
}