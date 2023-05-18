package com.example.carcollection.di

import com.example.carcollection.base.room.data.CarItemDao
import com.example.carcollection.base.room.data.CarRepositoryImpl
import com.example.carcollection.base.room.domain.CarRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [RoomModule::class])
object AppModule {
    @Provides
    @Singleton
    fun provideCarsRepository(carItemDao: CarItemDao): CarRepository {
        return CarRepositoryImpl(carItemDao)
    }
}