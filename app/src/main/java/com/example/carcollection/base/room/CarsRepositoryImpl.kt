package com.example.carcollection.base.room

class CarsRepository(private val carItemDao: CarItemDao) {

    suspend fun insert(car: Cars) {
        carItemDao.insertCar(car)
    }

    suspend fun update(car: Cars) {
        carItemDao.update(car)
    }

    suspend fun delete(car: Cars) {
        carItemDao.delete(car)
    }

    suspend fun getAllCars(): List<Cars> {
        return carItemDao.getAllCars()
    }
}