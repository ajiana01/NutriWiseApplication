package ch2ps299.ajiananta.nutriwise.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch2ps299.ajiananta.nutriwise.data.WiseRepository
import ch2ps299.ajiananta.nutriwise.model.Recipe
import ch2ps299.ajiananta.nutriwise.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class RecipesViewModel(
    private val wiseRepository: WiseRepository
): ViewModel() {
    private val _query = mutableStateOf("")
    val query: State<String> get() = _query
    private val _uiState: MutableStateFlow<UiState<List<Recipe>>> = MutableStateFlow(UiState.Loading)
    val uiState: MutableStateFlow<UiState<List<Recipe>>>
        get() = _uiState
    private val _selectedTags = mutableStateOf(setOf<String>())
    val selectedTags: State<Set<String>> = _selectedTags

    fun searchRecipes(query: String) {
        _query.value = query
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            wiseRepository.searchRecipes(query)
                .catch { _uiState.value = UiState.Error(it.message.toString()) }
                .collect { recipes ->
                    _uiState.value = UiState.Success(recipes)
                }
        }
    }
    fun getAllRecipes() {
        viewModelScope.launch {
            wiseRepository.getAllRecipes()
                .catch { _uiState.value = UiState.Error(it.message.toString())  }
                .collect { recipes ->
                    _uiState.value = UiState.Success(recipes)
                }
        }
    }

    // Meng-update resep berdasarkan tag yang dipilih
    fun updateRecipesForSelectedTags() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val recipes = wiseRepository.getAllRecipes().first() // Collects the Flow into a List
                val filteredRecipes = if (_selectedTags.value.isEmpty()) {
                    recipes
                } else {
                    recipes.filter { recipe ->
                        // Assuming recipe.tag is a List<String> or Set<String>
                        recipe.tag.any { tag -> _selectedTags.value.contains(tag) }
                    }
                }
                _uiState.value = UiState.Success(filteredRecipes)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun onTagSelected(tag: String, selected: Boolean) {
        val currentTags = _selectedTags.value.toMutableSet()
        if (selected) {
            currentTags.add(tag)
        } else {
            currentTags.remove(tag)
        }
        _selectedTags.value = currentTags
        updateRecipesForSelectedTags()
    }
}