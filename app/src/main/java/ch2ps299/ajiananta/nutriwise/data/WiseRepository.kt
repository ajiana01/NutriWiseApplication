package ch2ps299.ajiananta.nutriwise.data

import android.util.Log
import ch2ps299.ajiananta.nutriwise.api.ApiService
import ch2ps299.ajiananta.nutriwise.model.ChildData
import ch2ps299.ajiananta.nutriwise.model.Recipe
import ch2ps299.ajiananta.nutriwise.model.RecipesDataList
import ch2ps299.ajiananta.nutriwise.model.SendChildData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class WiseRepository(
    private val apiService: ApiService,
){
    private val recipesData = mutableListOf<Recipe>()

    init {
        RecipesDataList.RecipesData.forEach {
            recipesData.add(it)
        }
    }
    fun getAllRecipes(): Flow<List<Recipe>> {
        return flowOf(recipesData)
    }

    fun searchRecipes(query: String): Flow<List<Recipe>> {
        return flowOf(recipesData.filter {
            it.name.contains(query, ignoreCase = true)
        })
    }

    fun getRecipeById(id: Long): Recipe {
        return recipesData.first {
            it.id == id
        }
    }

    fun getRandomRecipes(): Flow<List<Recipe>> {
        return flowOf(recipesData.shuffled().take(2))
    }

    fun getRandomRecipes4(): Flow<List<Recipe>> {
        return flowOf(recipesData.shuffled().take(4))
    }

    suspend fun getChildren(): Flow<List<ChildData>> = flow {
        val response = apiService.getChildren()
        if (response.isSuccessful) {
            emit(response.body() ?: listOf())
        } else {
            throw Exception("Failed to fetch children")
        }
    }.catch { e ->
        throw e
    }
    fun getChildById(id: String): Flow<SendChildData?> = flow {
        val response = apiService.getChildById(id)
        if (response.isSuccessful) {
            Log.d("ChildData", "Data received: ${response.body()}")
            emit(response.body())
        } else {
            Log.d("ChildData", "Failed to get data: ${response.errorBody()?.string()}")
            emit(null)
        }
    }.catch {
        emit(null)
    }

    fun postChild(child: SendChildData): Flow<Boolean> = flow {
        val response = apiService.postChild(child)
        Log.d("ChildData", "Data posted: ${response.body()}")
        if (response.isSuccessful) {
            Log.d("ChildData", "Data posted: ${response.body()}")
            emit(true)
        } else {
            Log.d("ChildData", "Failed to post data: ${response.errorBody()?.string()}")
            throw Exception("Failed to post child data")
        }
    }.catch {
        emit(false)
    }

    fun deleteChildById(id: String): Flow<Boolean> = flow {
        val response = apiService.deleteChildById(id)
        if (response.isSuccessful) {
            emit(true)
        } else {
            throw Exception("Failed to delete child")
        }
    }.catch {
        emit(false)
    }


    companion object {
        @Volatile
        private var INSTANCE: WiseRepository? = null

        fun getInstance(apiService: ApiService): WiseRepository =
            INSTANCE ?: synchronized(this) {
                WiseRepository(apiService).apply {
                    INSTANCE = this
                }
            }
    }
}