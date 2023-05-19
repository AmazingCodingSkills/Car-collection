package com.example.core.subscribe.domain

interface CounterRepository {

    fun inc(type:OperationType)

    fun clearInc()

    fun getCounter(type:OperationType): Int

}