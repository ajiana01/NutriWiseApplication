package ch2ps299.ajiananta.nutriwise.di

import ch2ps299.ajiananta.nutriwise.data.WiseRepository


object DataInjection {

    fun provideRepository(): WiseRepository {
        return WiseRepository.getInstance()
    }
}