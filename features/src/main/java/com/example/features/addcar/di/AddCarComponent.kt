package com.example.features.addcar.di

import com.example.core.di.AppComponent
import com.example.core.di.FragmentScope
import dagger.Component
import dagger.Module

@Module
class AddCarModule {

}

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [AddCarModule::class])
interface AddCarComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): AddCarComponent
    }

}