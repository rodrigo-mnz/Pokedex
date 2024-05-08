package com.example.pokedex.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokedex.data.PokeDexRepository
import com.example.pokedex.data.PokemonPagingSource
import com.example.pokedex.ui.model.PokemonUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach

class HomeViewModel(
    private val repository: PokeDexRepository,
    private val pokemonPagingSource: PokemonPagingSource
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun fetchFirstPage() {
        repository.fetchPokemonList(50, 0)
            .map { result ->
                result.onSuccess {
                    return@map HomeUiState.Success
                }
                return@map HomeUiState.Error
            }
            .onEach { _uiState.value = it }
            .launchIn(viewModelScope)
    }

    val pokemonList: Flow<PagingData<PokemonUIModel>> = Pager(
        config = PagingConfig(
            pageSize = 50,
            enablePlaceholders = true
        ),
        pagingSourceFactory = { pokemonPagingSource }
    ).flow
        .onCompletion { throwable ->
            if (throwable == null) {
                _uiState.value = HomeUiState.Success
            } else {
                _uiState.value = HomeUiState.Error
            }
        }
}

sealed interface HomeUiState {

    data object Loading : HomeUiState

    data object Error : HomeUiState

    data object Success : HomeUiState
}
