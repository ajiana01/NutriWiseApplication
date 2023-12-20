package ch2ps299.ajiananta.nutriwise.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import ch2ps299.ajiananta.nutriwise.data.StuntingRepository
import ch2ps299.ajiananta.nutriwise.data.WiseRepository

class ViewModelFactory(private val repository: WiseRepository, private val stuntingRepository: StuntingRepository): ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RecipesViewModel::class.java)) {
                return RecipesViewModel(repository) as T
            } else if (modelClass.isAssignableFrom(DetailFoodViewModel::class.java)) {
                return DetailFoodViewModel(repository) as T
            } else if (modelClass.isAssignableFrom(StuntingCheckResultViewModel::class.java)) {
                return StuntingCheckResultViewModel(repository) as T
            } else if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel() as T
            } else if (modelClass.isAssignableFrom(ChildDataViewModel::class.java)) {
                return ChildDataViewModel(repository, stuntingRepository) as T
            } else if (modelClass.isAssignableFrom(AddDataChildViewModel::class.java)) {
                return AddDataChildViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
}