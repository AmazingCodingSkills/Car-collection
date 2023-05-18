package com.example.features.addcar.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.room.data.Cars
import com.example.core.room.domain.CarRepository
import com.example.features.cars.presentation.CarsViewState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class AddCarViewModel( private val carRepository: CarRepository) : ViewModel() {

    private var carName: String = ""
    private var carImage: String = ""
    private var carYear: Int = 0
    private var carEngine: Double = 0.0

    private val viewState = MutableStateFlow<AddCarViewState>(AddCarViewState.Loading)
    val state: StateFlow<AddCarViewState> = viewState.asStateFlow()

    private val viewEvents = Channel<AddCarViewEvents>(Channel.UNLIMITED)
    val events: Flow<AddCarViewEvents> = viewEvents.receiveAsFlow()

    fun handleAction(action: AddCarViewAction) {
        when (action) {
            is AddCarViewAction.NameCar -> setCarName(action.input)
            is AddCarViewAction.YearCar -> setCarYear(action.input)
            is AddCarViewAction.EngineCar -> setCarEngine(action.input)
            is AddCarViewAction.ImageCar -> setCarImage(action.input)
            is AddCarViewAction.SaveCar -> saveCarToDatabase()
        }
    }



    private fun setCarName(name: String) {
        carName = name
    }

    private fun setCarImage(image: String) {
        carImage = image
    }

    private fun setCarYear(year: Int) {
        carYear = year
    }

    private fun setCarEngine(engine: Double) {
        carEngine = engine
    }



    private fun saveCarToDatabase() {

        val car = Cars(
            name = carName,
            year = carYear,
            engine = carEngine,
            image = carImage,
            date = Date().time
        )
        viewModelScope.launch {
            try {
                carRepository.insertCar(car)
                viewEvents.trySend(AddCarViewEvents.DismissScreen)
            } catch (e: Exception) {
                viewState.value = AddCarViewState.Empty
            }
        }
        viewState.value = AddCarViewState.Empty
    }
}