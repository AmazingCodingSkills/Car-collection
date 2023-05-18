package com.example.core.room.data

import androidx.room.*
import com.example.core.room.Cars

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