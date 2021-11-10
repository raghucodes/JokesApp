package com.example.jokesapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.jokesapp.data.dao.JokesDao
import com.example.jokesapp.domain.entities.JokeEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [JokeEntity::class],version = 1)
abstract class JokesDatabase : RoomDatabase(){
    abstract fun jokesDao() : JokesDao

    companion object {
        @Volatile
        private var INSTANCE: JokesDatabase? = null

        fun getDatabase(
            context: Context
        ): JokesDatabase {

            return INSTANCE
                ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        JokesDatabase::class.java,
                        "jokes_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                    instance
                }

        }
    }
}