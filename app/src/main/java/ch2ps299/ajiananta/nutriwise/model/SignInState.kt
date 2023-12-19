package ch2ps299.ajiananta.nutriwise.model

data class SignInState(
    val isSignIsSuccessful: Boolean = false,
    val signInError: String? = null,
)
