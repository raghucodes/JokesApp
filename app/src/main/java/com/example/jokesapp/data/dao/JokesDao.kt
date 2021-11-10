package com.example.jokesapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.jokesapp.domain.entities.JokeEntity

@Dao
interface JokesDao {

    @Insert
    fun insertSavedJoke(joke: JokeEntity)

    @Query("SELECT * FROM saved_jokes")
    fun getSavedJokes() : LiveData<List<JokeEntity>>

    @Delete
    fun deleteJoke(joke : JokeEntity)

}