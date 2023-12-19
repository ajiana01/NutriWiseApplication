package ch2ps299.ajiananta.nutriwise.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch2ps299.ajiananta.nutriwise.data.WiseRepository
import ch2ps299.ajiananta.nutriwise.model.Recipe
import ch2ps299.ajiananta.nutriwise.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailFoodViewModel(private val repository: WiseRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Recipe>> = MutableStateFlow(UiState.Loading)
    val uiState: MutableStateFlow<UiState<Recipe>>
        get() = _uiState

    fun getRecipeById(id: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getRecipeById(id))
        }
    }
}