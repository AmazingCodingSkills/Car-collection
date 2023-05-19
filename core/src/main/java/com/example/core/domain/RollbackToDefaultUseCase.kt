package com.example.core.domain

import com.example.core.subscribe.domain.CounterRepository
import com.example.core.subscribe.domain.PremiumRepository
import javax.inject.Inject

class RollbackToDefaultUseCase @Inject constructor(
    private val counterRepository: CounterRepository,
    private val premiumRepository: PremiumRepository
) {
     fun clearCounterAndCancelSubscription() {
        counterRepository.clearInc()
        premiumRepository.cancelSubscription()
    }

}