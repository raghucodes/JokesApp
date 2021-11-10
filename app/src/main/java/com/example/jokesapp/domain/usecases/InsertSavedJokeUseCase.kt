package com.example.jokesapp.domain.usecases

import com.example.jokesapp.data.repository.JokesRepository
import com.example.jokesapp.domain.entities.JokeEntity

class InsertSavedJokeUseCase(private val jokesRepository: JokesRepository) {
    suspend fun insertJoke(jokeEntity: JokeEntity){ jokesRepository.insertSavedJoke(jokeEntity) }
}