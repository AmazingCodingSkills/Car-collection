package com.example.core.room.data

import com.example.core.room.domain.CarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(private val carItemDao: CarItemDao) : CarRepository {

    private val onEvent = MutableSharedFlow<Unit>(replay = 1)

    override fun onUpdate(): Flow<Unit> = onEvent

    override suspend fun insertCar(cars: Cars) {
        carItemDao.insertCar(cars)
        onEvent.tryEmit(Unit)
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