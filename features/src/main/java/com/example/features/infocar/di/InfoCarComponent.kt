package com.example.features.infocar.di

import com.example.core.di.AppComponent
import com.example.core.di.FragmentScope
import com.example.core.domain.IsAvailableUseCase
import com.example.core.subscribe.domain.PremiumRepository
import com.example.features.infocar.presentation.InfoCarViewModelFactory
import dagger.Component
import dagger.Module
import dagger.Provides

@Module()
class InfoCarModule() {

    @Provides
    fun factoryInfoCarViewModel(
        isAvailableUseCase: IsAvailableUseCase,
        premiumRepository: PremiumRepository,
    ): InfoCarViewModelFactory =
        InfoCarViewModelFactory(isAvailableUseCase, premiumRepository)
}

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [InfoCarModule::class])
interface InfoCarComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): InfoCarComponent
    }

    fun factoryInfoCarViewModel(): InfoCarViewModelFactory

}