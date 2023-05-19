package com.example.features.settings.di

import com.example.core.di.AppComponent
import com.example.core.di.FragmentScope
import com.example.core.domain.RollbackToDefaultUseCase
import com.example.features.settings.presentation.SetViewModelFactory
import dagger.Component
import dagger.Module
import dagger.Provides

@Module()
class InfoCarModule() {

    @Provides
    fun factorySetViewModel(
        rollbackToDefaultUseCase: RollbackToDefaultUseCase
    ): SetViewModelFactory =
        SetViewModelFactory(rollbackToDefaultUseCase)
}

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [InfoCarModule::class])
interface SetComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): SetComponent
    }

    fun factorySetViewModel(): SetViewModelFactory

}