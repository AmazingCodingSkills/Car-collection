package com.example.carcollection.base.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "cars")
data class Cars(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val name: String,
    val image: String,
    val year: Int,
    val engine: Double,
    val date: Long
)