package com.example.core.subscribe.domain

interface PremiumRepository {

     fun buySubscription()

     fun cancelSubscription()

     fun getProfile(): Subscription
}