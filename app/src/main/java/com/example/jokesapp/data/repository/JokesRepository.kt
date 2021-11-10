package com.example.jokesapp.data.repository

import android.util.Log
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jokesapp.data.api.interfaces.BASE_URL
import com.example.jokesapp.data.api.interfaces.JokesApi
import com.example.jokesapp.data.api.models.ResponseData
import com.example.jokesapp.data.api.models.ResponseDataItem
import com.example.jokesapp.data.dao.JokesDao
import com.example.jokesapp.data.retrofit.RetrofitInstance
import com.example.jokesapp.domain.entities.JokeEntity
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class JokesRepository(private val jokesApi: JokesApi,private val jokesDao: JokesDao){

    @WorkerThread
    fun getSavedJokes() : LiveData<List<JokeEntity>> = jokesDao.getSavedJokes()

    @WorkerThread
    fun deleteSavedJoke(jokeEntity: JokeEntity){ jokesDao.deleteJoke(jokeEntity) }

    @WorkerThread
    fun insertSavedJoke(jokeEntity: JokeEntity){ jokesDao.insertSavedJoke(jokeEntity) }

    fun getRandomJoke(jokeType : String,quantity : String) : LiveData<List<ResponseDataItem>> {
         val jokeResponse = MutableLiveData<List<ResponseDataItem>>()
        jokesApi.getRandomOne(jokeType,quantity).enqueue(object : Callback<List<ResponseDataItem>>{
            override fun onFailure(call: Call<List<ResponseDataItem>>, t: Throwable) {
                Log.e("failure","can't connect to api")
                Log.e("error",t.toString())
            }
            override fun onResponse(
                call: Call<List<ResponseDataItem>>,
                response: Response<List<ResponseDataItem>>
            ) {
                jokeResponse.value = response.body()
            }
        })
        return jokeResponse
    }

}