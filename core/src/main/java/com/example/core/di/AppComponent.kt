package com.example.core.di

import android.app.Application
import android.content.SharedPreferences
import com.example.core.room.data.CarItemDao
import com.example.core.room.domain.CarRepository
import com.example.core.subscribe.data.CounterRepositoryImpl
import com.example.core.subscribe.domain.CounterRepository
import com.example.core.subscribe.domain.PremiumRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RoomModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }

    fun providesCarRepository(): CarRepository

    fun providesRoom(): CarItemDao

    fun providesCounterRepository(): CounterRepository

    fun providesPremiumRepository(): PremiumRepository

    fun sharedPreferences(): SharedPreferences

}

@Scope
annotation class FragmentScope
