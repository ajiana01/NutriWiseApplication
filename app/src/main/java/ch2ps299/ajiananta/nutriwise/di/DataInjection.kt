package ch2ps299.ajiananta.nutriwise.di

import ch2ps299.ajiananta.nutriwise.api.ApiService
import ch2ps299.ajiananta.nutriwise.api.StuntingService
import ch2ps299.ajiananta.nutriwise.data.StuntingRepository
import ch2ps299.ajiananta.nutriwise.data.WiseRepository
import ch2ps299.ajiananta.nutriwise.utils.Endpoints
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


object DataInjection {
    fun provideRepository(): WiseRepository {
        val retrofit = Retrofit.Builder()
            .baseUrl(Endpoints.CHILD_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        return WiseRepository.getInstance(apiService)
    }
}

object RetrofitClient {
    fun provideRepository2(): StuntingRepository {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Endpoints.STUNTING_URL)
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(StuntingService::class.java)
        return StuntingRepository.getInstance(apiService)
    }
}