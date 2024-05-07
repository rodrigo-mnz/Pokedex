package com.example.pokedex.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.pokedex.R
import com.example.pokedex.extensions.findColorFromType
import com.example.pokedex.ui.model.PokemonUIModel
import org.koin.androidx.compose.koinViewModel

// TODO: Previews
@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onPokemonSelected: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    val lazyPagingItems: LazyPagingItems<PokemonUIModel> =
        viewModel.pokemonList.collectAsLazyPagingItems()

    LaunchedEffect(key1 = true) {
        viewModel.fetchFirstPage()
    }

    when (uiState) {
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
            HomeContent(lazyPagingItems, onPokemonSelected)
        }
    }
}

@Composable
private fun HomeContent(
    lazyPagingItems: LazyPagingItems<PokemonUIModel>,
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
            items(lazyPagingItems.itemCount) { index ->
                val pokemon = lazyPagingItems[index] ?: return@items

                Column(
                    modifier = Modifier.clickable {
                        onPokemonSelected.invoke(pokemon.name)
                    }
                ) {
                    Box(
                        modifier = Modifier.background(
                            color = com.example.pokedex.ui.theme.LightGray,
                            shape = RoundedCornerShape(8.dp)
                        )
                    ) {
                        AsyncImage(
                            model = pokemon.image,
                            placeholder = painterResource(id = R.drawable.placeholder),
                            contentDescription = "${pokemon.name}'s image"
                        )
                    }

                    Text(text = "#" + pokemon.id.toString().padStart(4, '0'))

                    Text(text = pokemon.name)

                    Spacer(modifier = Modifier.height(8.dp))

                    Row {
                        pokemon.types.forEach {
                            val color = it.findColorFromType()

                            Text(
                                text = it,
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .background(
                                        color = color,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .padding(vertical = 2.dp, horizontal = 4.dp)
                            )

                            Spacer(modifier = Modifier.width(2.dp))
                        }
                    }
                }
            }
        }
    }
}
