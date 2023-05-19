package com.example.core.subscribe.domain

sealed interface Subscription {

    data class Trial(
        val showCarDetailsLimit: Int,
        val addNewCarLimit: Int
    ) : Subscription

    object Full: Subscription
}