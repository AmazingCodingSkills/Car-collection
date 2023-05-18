package com.example.features.addcar.presentation

interface AddCarViewAction {

    data class NameCar (val input: String) : AddCarViewAction

    data class YearCar (val input: Int) : AddCarViewAction

    data class EngineCar (val input: Double) : AddCarViewAction

    data class ImageCar (val input: String) : AddCarViewAction

    data class Date (val input: Long) : AddCarViewAction

    object SaveCar: AddCarViewAction

}