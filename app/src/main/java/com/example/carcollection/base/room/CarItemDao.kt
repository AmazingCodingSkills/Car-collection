package com.example.carcollection.base.room

import androidx.room.*

@Dao
interface CarItemDao {

    @Insert
    suspend fun insertCar(cars: Cars)

    @Update
    suspend fun update(car: Cars)

    @Delete
    suspend fun delete(car: Cars)

    @Query("SELECT * FROM cars")
    suspend fun getAllCars(): List<Cars>

}