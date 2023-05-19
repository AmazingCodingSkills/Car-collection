package com.example.features.addcar.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.IsAvailableUseCase
import com.example.core.room.data.Cars
import com.example.core.room.domain.CarRepository
import com.example.core.subscribe.domain.OperationType
import com.example.core.subscribe.domain.PremiumRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class AddCarViewModel(
    private val carRepository: CarRepository,
    private val isAvailableUseCase: IsAvailableUseCase,
    private val premiumRepository: PremiumRepository,
) : ViewModel() {

    private var carName: String = ""
    private var carImage: String = ""
    private var carYear: String = ""
    private var carEngine: String = ""

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
        carYear = year.toString()
    }

    private fun setCarEngine(engine: Double) {
        carEngine = engine.toString()
    }


    fun buySub() {
        premiumRepository.buySubscription()
    }

    private fun saveCarToDatabase() {
        if (carName.isEmpty() || carYear.isEmpty() || carEngine.isEmpty() || carImage.isEmpty()) {
            viewEvents.trySend(AddCarViewEvents.ValidationErrorDialog)
            return
        }
        val car = Cars(
            name = carName,
            year = carYear.toInt(),
            engine = carEngine.toDouble(),
            image = carImage,
            date = Date().time
        )
        viewModelScope.launch {
            try {
                if (!isAvailableUseCase(OperationType.ADD_NEW_CAR)) {
                    viewEvents.trySend(AddCarViewEvents.ShowDialog)
                    return@launch
                } else {
                    carRepository.insertCar(car)
                    viewEvents.trySend(AddCarViewEvents.DismissScreen)
                }

            } catch (e: Exception) {
                Log.e("Error", "Error:$e")
            }
        }
    }
}
