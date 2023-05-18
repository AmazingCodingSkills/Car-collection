package com.example.carcollection.addcar.di

import com.example.carcollection.di.AppComponent
import com.example.carcollection.di.FragmentScope
import dagger.Component
import dagger.Module
import dagger.Provides

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