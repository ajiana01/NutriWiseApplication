package ch2ps299.ajiananta.nutriwise.model

data class ChildData (
    val id: String,
    val child_name: String,
    val date_of_birth: String,
    val height: Double,
    val weight: Double,
    val gender: String,
    val head_circumference: Double
)

data class SendChildData (
    val child_name: String,
    val date_of_birth: String,
    val height: Double,
    val weight: Double,
    val gender: String,
    val head_circumference: Double
)