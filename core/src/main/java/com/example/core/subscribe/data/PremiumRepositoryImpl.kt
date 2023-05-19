package com.example.core.subscribe.data

import com.example.core.subscribe.domain.PremiumRepository
import com.example.core.subscribe.domain.Subscription

class PremiumRepositoryImpl() : PremiumRepository {


    private var profile: Subscription = trial


    override fun buySubscription() {
        profile = Subscription.Full
    }

    override  fun cancelSubscription() {
        profile = trial
    }

    override  fun getProfile(): Subscription {
        return profile
    }

    companion object {
        private var trial: Subscription =
            Subscription.Trial(showCarDetailsLimit = 3, addNewCarLimit = 2)
    }

}