package com.example.core.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class Cars(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val name: String,
    val image: String,
    val year: Int,
    val engine: Double,
    val date: Long
)