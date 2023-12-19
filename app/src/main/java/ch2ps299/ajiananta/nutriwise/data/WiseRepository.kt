package ch2ps299.ajiananta.nutriwise.data

import ch2ps299.ajiananta.nutriwise.model.Recipe
import ch2ps299.ajiananta.nutriwise.model.RecipesDataList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class WiseRepository{
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

    companion object {
        @Volatile
        private var INSTANCE: WiseRepository? = null

        fun getInstance(): WiseRepository =
            INSTANCE ?: synchronized(this) {
                WiseRepository().apply {
                    INSTANCE = this
                }
            }
    }
}