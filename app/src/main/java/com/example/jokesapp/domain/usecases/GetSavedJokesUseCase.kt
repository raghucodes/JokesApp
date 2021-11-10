package com.example.jokesapp.domain.usecases

import androidx.lifecycle.LiveData
import com.example.jokesapp.data.repository.JokesRepository
import com.example.jokesapp.domain.entities.JokeEntity

class GetSavedJokesUseCase(private val jokesRepository: JokesRepository) {
     fun invoke() : LiveData<List<JokeEntity>> = jokesRepository.getSavedJokes()
}