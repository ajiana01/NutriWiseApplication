package ch2ps299.ajiananta.nutriwise.data

import android.util.Log
import ch2ps299.ajiananta.nutriwise.api.StuntingService
import ch2ps299.ajiananta.nutriwise.model.PredictBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class StuntingRepository(
   private val stuntingService: StuntingService
) {
    fun postPredict(body: PredictBody): Flow<String> = flow {
        val response = stuntingService.postPredict(body)
        if (response.isSuccessful) {
            Log.d("Predict", "Data posted: ${response.body()}")
            emit(response.body()?.result ?: "No result")
        } else {
            Log.d("Predict", "Failed to post data: ${response.errorBody()?.string()}")
            throw Exception("Failed to post predict data")
        }
    }.catch { exception ->
        Log.e("Predict", "Error: ${exception.message}")
        emit("Failed to post predict data: ${exception.message}")
    }

    companion object {
        @Volatile
        private var instance: StuntingRepository? = null

        fun getInstance(stuntingService: StuntingService): StuntingRepository =
            instance ?: synchronized(this) {
                StuntingRepository(stuntingService).apply {
                    instance = this
                }
            }
    }
}