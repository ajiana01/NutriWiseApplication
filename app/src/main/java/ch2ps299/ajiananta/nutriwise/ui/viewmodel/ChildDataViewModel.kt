package ch2ps299.ajiananta.nutriwise.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch2ps299.ajiananta.nutriwise.data.StuntingRepository
import ch2ps299.ajiananta.nutriwise.data.WiseRepository
import ch2ps299.ajiananta.nutriwise.model.ChildData
import ch2ps299.ajiananta.nutriwise.model.PredictBody
import ch2ps299.ajiananta.nutriwise.model.SendChildData
import ch2ps299.ajiananta.nutriwise.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ChildDataViewModel(
    private val repository: WiseRepository,
    private val stuntingRepository: StuntingRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<ChildData>>> = MutableStateFlow(UiState.Loading)
    val uiState: MutableStateFlow<UiState<List<ChildData>>>
        get() = _uiState


    private val _deleteStatus = MutableStateFlow<UiState<Boolean>>(UiState.Loading)
    val deleteStatus: StateFlow<UiState<Boolean>> = _deleteStatus.asStateFlow()

    var selectedChildId = mutableStateOf<String?>(null)

    fun selectChild(id: String) {
        selectedChildId.value = id
    }

    fun getChildrenData() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            repository.getChildren()
                .catch { _uiState.value = UiState.Error(it.message.toString()) }
                .collect { child ->
                    _uiState.value = UiState.Success(child)
                }
        }
    }

    fun deleteChildById(id: String) {
        viewModelScope.launch {
            repository.deleteChildById(id)
                .onStart {
                    // Emit loading state
                    _deleteStatus.value = UiState.Loading
                }
                .catch { e ->
                    // Emit error state
                    _deleteStatus.value = UiState.Error(e.message ?: "An unknown error occurred")
                }
                .collect { isSuccess ->
                    // Emit success or error based on the result
                    if (isSuccess) {
                        _deleteStatus.value = UiState.Success(isSuccess)
                    } else {
                        _deleteStatus.value = UiState.Error("Failed to delete child")
                    }
                }
        }
    }

    private val _childData = MutableStateFlow<SendChildData?>(null)
    val childData: StateFlow<SendChildData?> = _childData.asStateFlow()

    fun fetchChildData(id: String) {
        viewModelScope.launch {
            repository.getChildById(id).collect { data ->
                _childData.value = data
            }
        }
    }

    var childGender = mutableStateOf(TextFieldValue())
        private set

    var childAge = mutableStateOf(TextFieldValue())
        private set
    var weightText = mutableStateOf(TextFieldValue())
        private set

    var heightText = mutableStateOf(TextFieldValue())
        private set

    var circleText = mutableStateOf(TextFieldValue())
        private set

    fun onWeightChange(newValue: TextFieldValue) {
        weightText.value = newValue
    }

    fun onHeightChange(newValue: TextFieldValue) {
        heightText.value = newValue
    }

    fun onCircleChange(newValue: TextFieldValue) {
        circleText.value = newValue
    }

    private val _uiStateStunting: MutableStateFlow<UiState<String>> = MutableStateFlow(UiState.Loading)
    val uiStateStunting: MutableStateFlow<UiState<String>>
        get() = _uiStateStunting

    fun postStuntingPrediction(body: PredictBody) {
        viewModelScope.launch {
            stuntingRepository.postPredict(body)
                .onStart {
                    _uiStateStunting.value = UiState.Loading
                }
                .catch { e ->
                    _uiStateStunting.value = UiState.Error(e.message ?: "An unknown error occurred")
                }
                .collect { response ->
                    _uiStateStunting.value = UiState.Success(response)
                }
            Log.d("TAG", "postStuntingPrediction: ${_uiStateStunting.value}")
        }
    }
}