package com.example.features.settings.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.RollbackToDefaultUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SetViewModel(
    private val rollbackToDefaultUseCase: RollbackToDefaultUseCase
) : ViewModel() {

    private val viewEvents = Channel<SetViewEvents>(Channel.UNLIMITED)
    val events: Flow<SetViewEvents> = viewEvents.receiveAsFlow()

    fun handleAction(action: SetViewAction) {
        when (action) {
            is SetViewAction.ReloadCounts -> reloadToDefault()
        }
    }

    private fun reloadToDefault() {

        viewModelScope.launch {
            try {
                rollbackToDefaultUseCase.clearCounterAndCancelSubscription()
            } catch (e: Exception) {
                Log.e("Error","Error:$e")
            }
        }
        viewEvents.trySend(SetViewEvents.DismissScreen)
    }
}
