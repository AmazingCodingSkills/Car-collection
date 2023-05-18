package com.example.core.di

import androidx.room.Room
import com.example.core.App
import com.example.core.room.AppDatabase
import com.example.core.room.data.CarItemDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Provides
    @Singleton
    fun providesRoom(): CarItemDao {
        val db = Room.databaseBuilder(
            App.application.applicationContext,
            AppDatabase::class.java, "Favorite"
        ).build()
        return db.carItemDao()
    }
}