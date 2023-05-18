package com.example.features.cars.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.room.domain.CarRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CarsViewModel(private val carRepository: CarRepository) : ViewModel() {

    private val viewState = MutableStateFlow<CarsViewState>(CarsViewState.Loading)
    val state: StateFlow<CarsViewState> = viewState.asStateFlow()

    private val viewEvents = Channel<CarsViewState>(Channel.UNLIMITED)
    val events: Flow<CarsViewState> = viewEvents.receiveAsFlow()

    init {
        loadCars()
        carRepository.onUpdate().onEach {
            loadCars()
        }.launchIn(viewModelScope)
    }

    private fun loadCars() {
        viewModelScope.launch {
            viewState.value = CarsViewState.Loading
            try {
                val allCarsCollection = carRepository.getAll()
                    viewState.value = CarsViewState.Content(allCarsCollection)
            } catch (throwable: Throwable) {
                // viewEvents.trySend()
            }
        }
    }

}