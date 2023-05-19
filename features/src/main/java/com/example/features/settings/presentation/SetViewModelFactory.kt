package com.example.features.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.core.domain.RollbackToDefaultUseCase
import javax.inject.Inject

class SetViewModelFactory @Inject constructor(
    private val rollbackToDefaultUseCase: RollbackToDefaultUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return SetViewModel(
            rollbackToDefaultUseCase
        ) as T
    }
}