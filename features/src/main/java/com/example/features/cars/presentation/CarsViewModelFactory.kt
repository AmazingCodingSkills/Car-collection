package com.example.features.cars.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.core.room.domain.CarRepository
import javax.inject.Inject

class CarsViewModelFactory @Inject constructor(
    private val carRepository: CarRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return CarsViewModel(carRepository) as T
    }
}