package com.example.core.domain

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.example.core.Constants
import com.example.core.subscribe.domain.CounterRepository
import com.example.core.subscribe.domain.OperationType
import com.example.core.subscribe.domain.PremiumRepository
import com.example.core.subscribe.domain.Subscription
import javax.inject.Inject

class IsAvailableUseCase @Inject constructor(
    private val counterRepository: CounterRepository,
    private val premiumRepository: PremiumRepository
) {
    @SuppressLint("CommitPrefEdits")
    operator fun invoke(
        type: OperationType
    ): Boolean {
        when (val profile = premiumRepository.getProfile()) {
            is Subscription.Full -> return true
            is Subscription.Trial -> {
                when (type) {
                    OperationType.SHOW_DETAILS -> {
                        val trialLimit = profile.showCarDetailsLimit
                        val nowCounter = counterRepository.getCounter(OperationType.SHOW_DETAILS)
                        if (nowCounter < trialLimit) {
                            counterRepository.inc(OperationType.SHOW_DETAILS)
                            return true
                        }
                    }
                    OperationType.ADD_NEW_CAR -> {
                        val trialLimit = profile.addNewCarLimit
                        val nowCounter = counterRepository.getCounter(OperationType.ADD_NEW_CAR)
                        if (nowCounter < trialLimit) {
                            counterRepository.inc(OperationType.ADD_NEW_CAR)
                            return true
                        }

                    }
                }
            }
        }
        return false
    }
}
