package com.example.features.infocar.presentation


sealed interface InfoCarViewEvents {

    object DismissScreen: InfoCarViewEvents

    object ShowDialog: InfoCarViewEvents

}