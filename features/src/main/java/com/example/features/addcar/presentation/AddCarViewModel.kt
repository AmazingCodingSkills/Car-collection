package com.example.features.addcar.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.room.data.Cars
import com.example.core.room.domain.CarRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddCarViewModel( val carRepository: CarRepository) : ViewModel() {

    private var carName: String = ""
    private var carImage: String = ""
    private var carYear: Int? = null
    private var carEngine: Double? = null
    private var date: Long? = null

    private val viewState = MutableStateFlow<AddCarViewState>(AddCarViewState.Loading)
    val state: StateFlow<AddCarViewState> = viewState.asStateFlow()

    fun handleAction(action: AddCarViewAction) {
        when (action) {
            is AddCarViewAction.NameCar -> setCarName(action.input)
            is AddCarViewAction.YearCar -> setCarYear(action.input)
            is AddCarViewAction.EngineCar -> setCarEngine(action.input)
            is AddCarViewAction.ImageCar -> setCarImage(action.input)
            is AddCarViewAction.Date -> setDate(action.input)
            is AddCarViewAction.SaveCar -> saveCarToDatabase()
        }
    }


    private fun setCarName(name: String) {
        carName = name
    }

    private fun setCarImage(image: String) {
        carImage = image
    }

    private fun setCarYear(year: Int?) {
        carYear = year
    }

    private fun setCarEngine(engine: Double?) {
        carEngine = engine
    }

    private fun setDate(date: Long?) {
        this.date = date
    }


    private fun saveCarToDatabase() {
        val car = Cars(
            name = carName,
            year = carYear!!,
            engine = carEngine!!,
            image = carImage,
            date = date!!
        )
        viewModelScope.launch {
            try {
                carRepository.insertCar(car)
                viewState.value = AddCarViewState.Success
            } catch (e: Exception) {
                viewState.value = AddCarViewState.Empty
            }
        }
        viewState.value = AddCarViewState.Empty
    }
}