package ch2ps299.ajiananta.nutriwise.api

import ch2ps299.ajiananta.nutriwise.model.ChildData
import ch2ps299.ajiananta.nutriwise.model.PredictBody
import ch2ps299.ajiananta.nutriwise.model.PredictResponse
import ch2ps299.ajiananta.nutriwise.model.SendChildData
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("children")
    suspend fun getChildren(): Response<List<ChildData>>

    @GET("children/{id}")
    suspend fun getChildById(@Path("id") id: String): Response<SendChildData>

    @POST("children")
    suspend fun postChild(@Body body: SendChildData): Response<ResponseBody>

    @DELETE("children/{id}")
    suspend fun deleteChildById(@Path("id") id: String): Response<ResponseBody>

}
