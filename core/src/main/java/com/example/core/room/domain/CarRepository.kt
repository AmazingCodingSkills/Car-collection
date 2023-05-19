package com.example.core.room.domain

import com.example.core.room.data.Cars
import kotlinx.coroutines.flow.Flow

interface CarRepository {

    fun onUpdate(): Flow<Unit>

    suspend fun insertCar(cars: Cars)

    suspend fun getAll(): List<Cars>

    fun searchCars(searchQuery: String): List<Cars>

}