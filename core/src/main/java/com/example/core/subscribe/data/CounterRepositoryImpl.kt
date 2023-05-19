package com.example.core.subscribe.data

import android.content.SharedPreferences
import com.example.core.Constants
import com.example.core.subscribe.domain.CounterRepository
import com.example.core.subscribe.domain.OperationType
import javax.inject.Inject

class CounterRepositoryImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    CounterRepository {


    override fun inc(type: OperationType) {
        when (type) {
            OperationType.SHOW_DETAILS -> {
                var counter = sharedPreferences.getInt(Constants.SHOW_CAR, 0)
                val carDetailsCounter = counter + 1
                sharedPreferences.edit().putInt(Constants.SHOW_CAR, carDetailsCounter).apply()
            }
            OperationType.ADD_NEW_CAR -> {
                var counter = sharedPreferences.getInt(Constants.COUNT_CAR, 0)
                val carCount = counter + 1
                sharedPreferences.edit().putInt(Constants.COUNT_CAR, carCount).apply()
            }
        }
    }

    override fun clearInc() {
        sharedPreferences.edit().clear().apply()
    }

    override fun getCounter(type: OperationType): Int {
        return when (type) {
            OperationType.SHOW_DETAILS -> sharedPreferences.getInt(Constants.SHOW_CAR, 0)
            OperationType.ADD_NEW_CAR -> sharedPreferences.getInt(Constants.COUNT_CAR, 0)
        }
    }
}