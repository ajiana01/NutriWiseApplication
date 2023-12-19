package ch2ps299.ajiananta.nutriwise.model

data class Recipe(
    val id: Long,
    val image: Int,
    val name: String,
    val tag: List<String>,
    val portion: Int,
    val age: String,
    val ingredients: String,
    val steps: String,
    val nutrition: List<Pair<String, String>>,
)