package com.example.carcollection.base.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.carcollection.base.room.data.CarItemDao


@Database(
    entities = [Cars::class], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun carItemDao(): CarItemDao
}

