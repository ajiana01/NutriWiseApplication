package ch2ps299.ajiananta.nutriwise.model


data class PredictBody(
    val input: List<Double>
)
data class PredictResponse(
    val result: String
)
