package com.example.jokesapp.ui.jokeactivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesapp.domain.entities.JokeEntity
import com.example.jokesapp.domain.usecases.GetJokesUseCase
import com.example.jokesapp.domain.usecases.InsertSavedJokeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokesViewModel(private val jokesUseCase: GetJokesUseCase,private val insertSavedJokeUseCase: InsertSavedJokeUseCase) : ViewModel() {
    //fun getRandomJoke(type:String, number:String) = GetJokesUseCase().getRandomJoke(type,number)
    fun getRandomJoke(type:String, number:String) = jokesUseCase.getRandomJoke(type,number)
    fun saveJoke(jokeEntity: JokeEntity) = viewModelScope.launch(Dispatchers.IO){
        insertSavedJokeUseCase.insertJoke(jokeEntity)
    }
}