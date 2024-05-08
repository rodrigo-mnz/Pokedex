package com.example.pokedex.ui

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pokedex.R
import com.example.pokedex.feature.detail.DetailScreen
import com.example.pokedex.feature.home.HomeScreen
import com.example.pokedex.ui.model.PokemonUIModel

@Composable
fun PokeDexApp(
    navController: NavHostController = rememberNavController()
) {
    var currentPokemon: PokemonUIModel? by remember { mutableStateOf(null) }
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = PokeDexScreen.valueOf(
        backStackEntry?.destination?.route ?: PokeDexScreen.Home.name
    )

    Scaffold(
        topBar = {
            PokeDexAppBar(
                currentScreen = currentScreen,
                currentPokemon = currentPokemon,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = PokeDexScreen.Home.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = PokeDexScreen.Home.name) {
                HomeScreen(
                    onPokemonSelected = {
                        currentPokemon = it
                        navController.navigate(PokeDexScreen.Detail.name)
                    }
                )
            }
            composable(route = PokeDexScreen.Detail.name) {
                currentPokemon?.let {
                    DetailScreen(currentPokemon = it)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokeDexAppBar(
    currentScreen: PokeDexScreen,
    currentPokemon: PokemonUIModel?,
    canNavigateBack: Boolean,
    navigateUp: () -> Boolean,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            if (currentScreen == PokeDexScreen.Detail) {
                Text(currentPokemon?.name ?: "")
            } else {
                Text(stringResource(currentScreen.title))
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = { navigateUp.invoke() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

enum class PokeDexScreen(@StringRes val title: Int) {
    Home(title = R.string.app_name),
    Detail(title = R.string.detail),
}