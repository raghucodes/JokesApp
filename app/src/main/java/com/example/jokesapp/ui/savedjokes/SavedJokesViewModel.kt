package com.example.jokesapp.ui.savedjokes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesapp.domain.entities.JokeEntity
import com.example.jokesapp.domain.usecases.DeleteSavedJokeUseCase
import com.example.jokesapp.domain.usecases.GetSavedJokesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedJokesViewModel(private val deleteSavedJokeUseCase: DeleteSavedJokeUseCase,private val getSavedJokesUseCase: GetSavedJokesUseCase):ViewModel() {


    fun deleteJoke(jokeEntity: JokeEntity) = viewModelScope.launch(Dispatchers.IO) {
        deleteSavedJokeUseCase.deleteSavedJoke(jokeEntity)
    }


    fun getSavedJokes(): LiveData<List<JokeEntity>> {
       return getSavedJokesUseCase.invoke()
    }

}

