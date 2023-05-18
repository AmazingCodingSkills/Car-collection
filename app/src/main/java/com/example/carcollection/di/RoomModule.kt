package com.example.carcollection.di

import androidx.room.Room
import com.example.carcollection.App
import com.example.carcollection.base.room.AppDatabase
import com.example.carcollection.base.room.data.CarItemDao
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