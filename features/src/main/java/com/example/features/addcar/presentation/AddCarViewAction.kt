package com.example.features.addcar.presentation

sealed interface AddCarViewAction {

    data class NameCar (val input: String) : AddCarViewAction

    data class YearCar (val input: Int) : AddCarViewAction

    data class EngineCar (val input: Double) : AddCarViewAction

    data class ImageCar (val input: String) : AddCarViewAction

    object SaveCar: AddCarViewAction

}