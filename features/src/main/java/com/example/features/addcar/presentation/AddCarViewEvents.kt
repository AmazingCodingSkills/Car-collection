package com.example.features.addcar.presentation

sealed interface AddCarViewEvents {

    object DismissScreen: AddCarViewEvents

    object ShowDialog: AddCarViewEvents

    object ValidationErrorDialog: AddCarViewEvents
}