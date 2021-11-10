package com.example.jokesapp.data.api.interfaces

import com.example.jokesapp.data.api.models.ResponseData
import com.example.jokesapp.data.api.models.ResponseDataItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://official-joke-api.appspot.com/jokes/"
interface JokesApi {

    @GET("{type}/{quantity}")
    fun getRandomOne(@Path("type") type : String,@Path("quantity") quantity : String) : Call<List<ResponseDataItem>>

//    @GET("random")
//    fun getRandomOne() : Call<List<ResponseDataItem>>
}