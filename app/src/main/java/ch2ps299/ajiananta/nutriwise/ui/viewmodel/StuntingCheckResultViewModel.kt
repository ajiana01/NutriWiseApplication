package ch2ps299.ajiananta.nutriwise.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch2ps299.ajiananta.nutriwise.data.WiseRepository
import ch2ps299.ajiananta.nutriwise.model.Recipe
import ch2ps299.ajiananta.nutriwise.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class StuntingCheckResultViewModel(
    private val wiseRepository: WiseRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Recipe>>> = MutableStateFlow(UiState.Loading)
    val uiState: MutableStateFlow<UiState<List<Recipe>>>
        get() = _uiState

    fun getRandomRecipes() {
        viewModelScope.launch {
            wiseRepository.getRandomRecipes()
                .catch { _uiState.value = UiState.Error(it.message.toString())  }
                .collect { recipes ->
                    _uiState.value = UiState.Success(recipes)
                }
        }
    }
}