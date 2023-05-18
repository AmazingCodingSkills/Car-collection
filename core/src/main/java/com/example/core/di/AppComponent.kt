package com.example.core.di

import android.app.Application
import com.example.core.room.data.CarItemDao
import com.example.core.room.domain.CarRepository
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

}

@Scope
annotation class FragmentScope
