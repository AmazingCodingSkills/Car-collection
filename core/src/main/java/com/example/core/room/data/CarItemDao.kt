package com.example.core.room.data

import androidx.room.*

@Dao
interface CarItemDao {

    @Insert
    suspend fun insertCar(cars: Cars)

    @Query("SELECT * FROM cars")
    suspend fun getAllCars(): List<Cars>

    @Query("SELECT * FROM cars WHERE name LIKE :searchQuery OR year LIKE :searchQuery OR engine LIKE :searchQuery")
    fun searchCars(searchQuery: String): List<Cars>

}