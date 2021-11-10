package com.example.jokesapp.domain.usecases

import androidx.lifecycle.LiveData
import com.example.jokesapp.data.api.models.ResponseData
import com.example.jokesapp.data.api.models.ResponseDataItem
import com.example.jokesapp.data.repository.JokesRepository
import org.koin.java.KoinJavaComponent.inject

class GetJokesUseCase(private val jokesRepository: JokesRepository) {
    fun getRandomJoke(jokeType : String,quantity : String) : LiveData<List<ResponseDataItem>> = jokesRepository.getRandomJoke(jokeType,quantity)
}