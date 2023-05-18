package com.example.carcollection.di

import android.app.Application
import com.example.carcollection.base.room.data.CarItemDao
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
    fun providesRoom (): CarItemDao
}
@Scope
annotation class FragmentScope