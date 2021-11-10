package com.example.jokesapp.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_jokes")
data class JokeEntity (
    @PrimaryKey @ColumnInfo(name = "setup") val setUp : String,
    @ColumnInfo(name = "punchLine") val punchLine : String
)