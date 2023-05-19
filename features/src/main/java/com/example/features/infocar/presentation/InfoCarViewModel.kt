package com.example.features.infocar.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.IsAvailableUseCase
import com.example.core.subscribe.domain.OperationType
import com.example.core.subscribe.domain.PremiumRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class InfoCarViewModel(
    private val isAvailableUseCase: IsAvailableUseCase,
    private val premiumRepository: PremiumRepository
) : ViewModel() {


    private val viewEvents = Channel<InfoCarViewEvents>(Channel.UNLIMITED)
    val events: Flow<InfoCarViewEvents> = viewEvents.receiveAsFlow()

    fun handleAction(action: InfoCarViewAction) {
        when (action) {
            is InfoCarViewAction.CountViews -> countViews()
        }
    }

    fun buySub() {
        premiumRepository.buySubscription()
    }

    private fun countViews() {
        viewModelScope.launch {
            try {
                if (!isAvailableUseCase(OperationType.SHOW_DETAILS)) {
                    viewEvents.trySend(InfoCarViewEvents.ShowDialog)
                    return@launch
                }
            } catch (e: Exception) {
                Log.e("Error", "Error:$e")
            }
        }
    }
}



