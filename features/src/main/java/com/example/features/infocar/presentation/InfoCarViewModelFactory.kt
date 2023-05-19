package com.example.features.infocar.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.core.domain.IsAvailableUseCase
import com.example.core.subscribe.domain.PremiumRepository
import javax.inject.Inject

class InfoCarViewModelFactory @Inject constructor(
    private val isAvailableUseCase: IsAvailableUseCase,
    private val premiumRepository: PremiumRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return InfoCarViewModel(
            isAvailableUseCase,
            premiumRepository
        ) as T
    }
}