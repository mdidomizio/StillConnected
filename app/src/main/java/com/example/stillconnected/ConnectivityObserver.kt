package com.example.stillconnected

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    val connectivityState: Flow<Status>

    sealed interface Status {
        object Available : Status
        object Lost : Status
        object AirplaneMode : Status
    }
}
