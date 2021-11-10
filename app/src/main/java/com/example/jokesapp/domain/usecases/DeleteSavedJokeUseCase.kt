package com.example.jokesapp.domain.usecases

import com.example.jokesapp.data.repository.JokesRepository
import com.example.jokesapp.domain.entities.JokeEntity

class DeleteSavedJokeUseCase(private val jokesRepository: JokesRepository) {
    suspend fun deleteSavedJoke(joke: JokeEntity){
        jokesRepository.deleteSavedJoke(joke)
    }
}