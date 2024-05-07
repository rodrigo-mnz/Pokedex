package com.example.pokedex.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokedex.R
import com.example.pokedex.ui.model.PokemonUIModel
import org.koin.androidx.compose.koinViewModel


@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onPokemonSelected: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.fetchPokemonList()
    }

    when (val value = uiState) {
        is HomeUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = stringResource(id = R.string.generic_error),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        is HomeUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is HomeUiState.Success -> {
            HomeContent(value.list, onPokemonSelected)
        }
    }
}

@Composable
private fun HomeContent(
    data: List<PokemonUIModel>,
    onPokemonSelected: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(data) { pokemon ->
                Column {
                    Box(
                        modifier = Modifier.background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(8.dp)
                        )
                    ) {
                        AsyncImage(
                            model = pokemon.image,
                            contentDescription = "${pokemon.name}'s image"
                        )
                    }

                    Text(text = "#" + pokemon.id.toString().padStart(4, '0'))

                    Text(
                        text = pokemon.name, modifier = Modifier.clickable {
                            onPokemonSelected.invoke(pokemon.name)
                        }
                    )
                }
            }
        }
    }
}
