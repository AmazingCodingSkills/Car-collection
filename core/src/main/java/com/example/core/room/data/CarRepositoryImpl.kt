package com.example.core.room.data

import com.example.core.room.Cars
import com.example.core.room.domain.CarRepository
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(private val carItemDao: CarItemDao) : CarRepository {
    override suspend fun insertCar(cars: Cars) {
        carItemDao.insertCar(cars)
    }

    override suspend fun update(cars: Cars) {
        carItemDao.update(cars)
    }

    override suspend fun delete(cars: Cars) {
        carItemDao.delete(cars)
    }

    override suspend fun getAll(): List<Cars> {
        return carItemDao.getAllCars()
    }

}