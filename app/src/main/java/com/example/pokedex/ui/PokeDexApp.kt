package com.example.pokedex.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pokedex.R
import com.example.pokedex.home.HomeViewModel

@Composable
fun PokeDexApp(
    viewModel: HomeViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()

}

enum class PokeDexScreen(@StringRes val title: Int) {
    Home(title = R.string.app_name),
    Detail(title = R.string.detail),
}