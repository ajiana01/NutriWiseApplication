package ch2ps299.ajiananta.nutriwise.api

import ch2ps299.ajiananta.nutriwise.model.PredictBody
import ch2ps299.ajiananta.nutriwise.model.PredictResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface StuntingService {
    @POST("predict")
    suspend fun postPredict(@Body body: PredictBody): Response<PredictResponse>
}