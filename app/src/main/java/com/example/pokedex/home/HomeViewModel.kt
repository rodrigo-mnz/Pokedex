package com.example.pokedex.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.PokeDexRepository
import com.example.pokedex.network.model.PokemonList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach


class HomeViewModel(
    private val repository: PokeDexRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()


    fun fetchPokemonList() {
        repository.fetchPokemonList()
            .map { result ->
                result.onSuccess {
                    return@map HomeUiState.Success(it)
                }
                return@map HomeUiState.Error
            }
            .onEach { _uiState.value = it }
            .launchIn(viewModelScope)
    }
}

sealed interface HomeUiState {

    data object Loading : HomeUiState

    data object Error : HomeUiState

    data class Success(
        val list: PokemonList,
    ) : HomeUiState
}