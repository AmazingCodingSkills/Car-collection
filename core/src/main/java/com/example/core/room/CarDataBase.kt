package com.example.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.room.data.CarItemDao
import com.example.core.room.data.Cars


@Database(
    entities = [Cars::class], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun carItemDao(): CarItemDao
}

