package com.example.features.addcar.presentation

import com.example.core.room.data.Cars

sealed interface AddCarViewState {

    object Loading : AddCarViewState

    object Success : AddCarViewState

    data class Content(val cars: List<Cars>) : AddCarViewState

    object Empty : AddCarViewState
}