package com.example.features.addcar.di

import com.example.core.di.AppComponent
import com.example.core.di.FragmentScope
import com.example.core.di.RoomModule
import com.example.core.domain.IsAvailableUseCase
import com.example.core.room.domain.CarRepository
import com.example.core.subscribe.domain.PremiumRepository
import com.example.features.addcar.presentation.AddCarViewModelFactory
import dagger.Component
import dagger.Module
import dagger.Provides

@Module(includes = [RoomModule::class])
class AddCarModule() {

    @Provides
    fun factoryAddCarViewModel(
        carRepository: CarRepository,
        isAvailableUseCase: IsAvailableUseCase,
        premiumRepository: PremiumRepository
    ): AddCarViewModelFactory =
        AddCarViewModelFactory(
            carRepository,
            isAvailableUseCase,
            premiumRepository,
        )

}

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [AddCarModule::class])
interface AddCarComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): AddCarComponent
    }

    fun factoryAddCarViewModel(): AddCarViewModelFactory
}