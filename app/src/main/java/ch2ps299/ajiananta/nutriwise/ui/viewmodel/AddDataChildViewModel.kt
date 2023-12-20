package ch2ps299.ajiananta.nutriwise.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch2ps299.ajiananta.nutriwise.data.WiseRepository
import ch2ps299.ajiananta.nutriwise.model.SendChildData
import ch2ps299.ajiananta.nutriwise.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class AddDataChildViewModel(
    private val repository: WiseRepository
): ViewModel(){
    var childName = mutableStateOf(TextFieldValue())
        private set

    var childGender = mutableStateOf("")
        private set

    var weightText = mutableStateOf(TextFieldValue())
        private set

    var heightText = mutableStateOf(TextFieldValue())
        private set

    var circleText = mutableStateOf(TextFieldValue())
        private set

    var selectedDate = mutableStateOf("")

    // Fungsi untuk mengupdate state
    fun onChildNameChange(newValue: TextFieldValue) {
        childName.value = newValue
    }

    fun onWeightTextChange(newValue: TextFieldValue) {
        weightText.value = newValue
    }

    fun onHeightTextChange(newValue: TextFieldValue) {
        heightText.value = newValue
    }
    fun onCircleTextChange(newValue: TextFieldValue) {
        circleText.value = newValue
    }
    // StateFlow untuk menyimpan status penambahan anak
    private val _postStatus = MutableStateFlow<UiState<Boolean>>(UiState.Loading)
    val postStatus: StateFlow<UiState<Boolean>> = _postStatus.asStateFlow()

    // Fungsi untuk menambahkan anak
    fun postChild(childData: SendChildData) {
        viewModelScope.launch {
            repository.postChild(childData)
                .onStart {
                    _postStatus.value = UiState.Loading
                }
                .catch { e ->
                    _postStatus.value = UiState.Error(e.message ?: "An unknown error occurred")
                }
                .collect { isSuccess ->
                    if (isSuccess) {
                        _postStatus.value = UiState.Success(isSuccess)
                    } else {
                        _postStatus.value = UiState.Error("Failed to post child data")
                    }
                }
        }
    }
}