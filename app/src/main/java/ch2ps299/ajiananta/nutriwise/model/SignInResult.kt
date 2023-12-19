package ch2ps299.ajiananta.nutriwise.model

data class SignInResult (
    val data: UserData?,
    val errorMessage: String?,
)

data class UserData(
    val id: String,
    val email: String?,
    val name: String?,
    val photoUrl: String?,
)