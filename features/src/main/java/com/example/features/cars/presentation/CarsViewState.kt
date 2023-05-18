package com.example.features.cars.presentation

import com.example.core.room.data.Cars

sealed interface CarsViewState {

    object Loading : CarsViewState

    object Success : CarsViewState

    data class Content(val cars: List<Cars>) : CarsViewState

    object Empty : CarsViewState
}