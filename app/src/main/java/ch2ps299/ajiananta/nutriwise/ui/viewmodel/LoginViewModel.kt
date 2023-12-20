package ch2ps299.ajiananta.nutriwise.ui.viewmodel

import androidx.lifecycle.ViewModel
import ch2ps299.ajiananta.nutriwise.model.SignInResult
import ch2ps299.ajiananta.nutriwise.model.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel: ViewModel() {
    private val _signInState = MutableStateFlow(SignInState())
    val signInState = _signInState.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _signInState.update {it.copy(
            isSignIsSuccessful = result.data != null,
            signInError = result.errorMessage
        )}
    }

    fun resetState() {
        _signInState.update { SignInState() }
    }
}