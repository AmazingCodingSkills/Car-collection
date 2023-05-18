package com.example.carcollection.base.room.domain

import com.example.carcollection.base.room.Cars

interface CarRepository {

    suspend fun insertCar(cars: Cars)

    suspend fun update(cars: Cars)

    suspend fun delete(cars: Cars)

    suspend fun getAll(): List<Cars>

}