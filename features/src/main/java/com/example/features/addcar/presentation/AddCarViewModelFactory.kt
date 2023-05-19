package com.example.features.addcar.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.core.domain.IsAvailableUseCase
import com.example.core.room.domain.CarRepository
import com.example.core.subscribe.domain.PremiumRepository
import javax.inject.Inject

class AddCarViewModelFactory @Inject constructor(
    private val carRepository: CarRepository,
    private val isAvailableUseCase: IsAvailableUseCase,
    private val premiumRepository: PremiumRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return AddCarViewModel(
            carRepository,
            isAvailableUseCase,
            premiumRepository,
        ) as T
    }
}