package com.example.features.cars.di

import com.example.core.di.AppComponent
import com.example.core.di.FragmentScope
import com.example.core.room.domain.CarRepository
import com.example.core.di.RoomModule
import com.example.features.cars.presentation.CarsViewModelFactory
import dagger.Component
import dagger.Module
import dagger.Provides


@Module()
class CarsModule() {

    @Provides
    fun factoryCarsViewModel(carRepository: CarRepository): CarsViewModelFactory =
        CarsViewModelFactory(carRepository)

}

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [CarsModule::class])
interface CarsComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): CarsComponent
    }
    fun factoryCarsViewModel(): CarsViewModelFactory

}
