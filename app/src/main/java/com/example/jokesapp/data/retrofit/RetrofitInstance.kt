package com.example.jokesapp.data.retrofit

import com.example.jokesapp.data.api.interfaces.BASE_URL
import com.example.jokesapp.data.api.interfaces.JokesApi
import com.example.jokesapp.data.interceptors.ErrorInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {

    companion object{

        private val interceptor = Interceptor{ chain ->
            val url = chain.request()
                .url
                .newBuilder()
                .build()
            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)
        }
        fun httpClient() : OkHttpClient {
            return  OkHttpClient.Builder().apply {
                this.addInterceptor(interceptor)
                    .addNetworkInterceptor(StethoInterceptor())
                    .addInterceptor(ErrorInterceptor())
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(25, TimeUnit.SECONDS)
            }.build()
        }

        fun getRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }

        fun provideJokesDataApi(retrofit: Retrofit) = retrofit.create(JokesApi::class.java)

    }

}