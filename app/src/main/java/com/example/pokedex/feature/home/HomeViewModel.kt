package com.example.pokedex.feature.home

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.pokedex.data.PokeDexRepository
import com.example.pokedex.ui.model.PokemonUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion

class HomeViewModel(
    private val repository: PokeDexRepository,
    private val pokemonPagingSource: PagingSource<Int, PokemonUIModel>
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    val pokemonList: Flow<PagingData<PokemonUIModel>> = Pager(
        config = PagingConfig(
            pageSize = 30,
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
