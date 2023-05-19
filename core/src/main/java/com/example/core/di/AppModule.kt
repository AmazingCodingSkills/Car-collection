package com.example.core.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.core.Constants.PREFERENCES_FILE_NAME
import com.example.core.room.data.CarItemDao
import com.example.core.room.data.CarRepositoryImpl
import com.example.core.room.domain.CarRepository
import com.example.core.subscribe.data.CounterRepositoryImpl
import com.example.core.subscribe.data.PremiumRepositoryImpl
import com.example.core.subscribe.domain.CounterRepository
import com.example.core.subscribe.domain.PremiumRepository
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
    @Provides
    @Singleton
    fun provideCounterRepository(sharedPreferences: SharedPreferences): CounterRepository{
        return CounterRepositoryImpl(sharedPreferences)
    }
    @Provides
    @Singleton
    fun providePremiumRepository(): PremiumRepository {
        return PremiumRepositoryImpl()
    }

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)

    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

}