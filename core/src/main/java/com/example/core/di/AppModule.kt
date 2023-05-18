package com.example.core.di

import com.example.core.room.data.CarItemDao
import com.example.core.room.data.CarRepositoryImpl
import com.example.core.room.domain.CarRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module()
object AppModule {

    @Provides
    @Singleton
    fun provideCarRepository(carItemDao: CarItemDao): CarRepository {
        return CarRepositoryImpl(carItemDao)
    }
}