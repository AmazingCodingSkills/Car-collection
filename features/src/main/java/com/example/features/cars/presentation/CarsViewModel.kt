package com.example.features.cars.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.room.data.Cars
import com.example.core.room.domain.CarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class CarsViewModel(private val carRepository: CarRepository) : ViewModel() {

    private val viewState = MutableStateFlow<CarsViewState>(CarsViewState.Loading)
    val state: StateFlow<CarsViewState> = viewState.asStateFlow()


    fun searchCars(searchQuery: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val results = carRepository.searchCars(searchQuery)
            viewState.value = CarsViewState.Content(results)
        }
    }

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
                Log.e("Error", "Error:$throwable")
            }
        }
    }
}