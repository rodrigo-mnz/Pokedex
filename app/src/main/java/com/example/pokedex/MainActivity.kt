package com.example.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import com.example.pokedex.home.HomeUiState
import com.example.pokedex.home.HomeViewModel
import com.example.pokedex.ui.PokeDexApp
import com.example.pokedex.ui.theme.PokedexTheme
import org.koin.java.KoinJavaComponent.inject

class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by inject(HomeViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            PokedexTheme {
                PokeDexApp()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.fetchPokemonList()
    }
}

@Composable
fun Greeting(viewModel: HomeViewModel, modifier: Modifier) {
    val homeUiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (homeUiState) {
        is HomeUiState.Error -> {
            Text(text = "Error")
        }

        is HomeUiState.Loading -> {
            Text(text = "Loading")
        }

        is HomeUiState.Success -> {
            Column {
                (homeUiState as HomeUiState.Success).list.results.forEach {
                    Text(text = it.name)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokedexTheme {
//        Greeting("Android")
    }
}