package com.example.jokesapp

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.jokesapp.data.database.JokesDatabase
import com.example.jokesapp.data.repository.JokesRepository
import com.example.jokesapp.data.retrofit.RetrofitInstance.Companion.getRetrofitInstance
import com.example.jokesapp.data.retrofit.RetrofitInstance.Companion.httpClient
import com.example.jokesapp.data.retrofit.RetrofitInstance.Companion.provideJokesDataApi
import com.example.jokesapp.domain.di.savedJokesApplicationContext
import com.example.jokesapp.domain.usecases.DeleteSavedJokeUseCase
import com.example.jokesapp.domain.usecases.GetJokesUseCase
import com.example.jokesapp.domain.usecases.GetSavedJokesUseCase
import com.example.jokesapp.domain.usecases.InsertSavedJokeUseCase
import com.example.jokesapp.ui.jokeactivity.JokesViewModel
import com.example.jokesapp.ui.networkstate.NetworkStateHolder.registerConnectivityMonitor
import com.example.jokesapp.ui.savedjokes.SavedJokesViewModel
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App:Application() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        registerConnectivityMonitor()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule))
        }
    }
}

var appModule = module{

    factory { httpClient() }

    factory { getRetrofitInstance(get()) }

    single { JokesDatabase.getDatabase(savedJokesApplicationContext).jokesDao() }

    single { provideJokesDataApi(get()) }

    factory { JokesRepository(get(),get()) }

    factory { GetJokesUseCase(get()) }

    factory { GetSavedJokesUseCase(get()) }

    factory { InsertSavedJokeUseCase(get()) }

    factory { DeleteSavedJokeUseCase(get()) }

     viewModel { SavedJokesViewModel(get(),get()) }

    viewModel { JokesViewModel(get(),get()) }

}