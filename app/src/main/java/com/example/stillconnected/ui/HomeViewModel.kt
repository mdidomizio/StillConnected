package com.example.stillconnected.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stillconnected.ConnectivityObserver
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val connectivityObserver: ConnectivityObserver
): ViewModel() {
    val connectivityStatus = connectivityObserver
        .connectivityState
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            ConnectivityObserver.Status.Lost
        )
}