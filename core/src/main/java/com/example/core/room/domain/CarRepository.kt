package com.example.core.room.domain

import com.example.core.room.data.Cars

interface CarRepository {

    suspend fun insertCar(cars: Cars)

    suspend fun update(cars: Cars)

    suspend fun delete(cars: Cars)

    suspend fun getAll(): List<Cars>

}