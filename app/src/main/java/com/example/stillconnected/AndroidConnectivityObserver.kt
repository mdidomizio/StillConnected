package com.example.stillconnected

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import androidx.core.content.getSystemService
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class AndroidConnectivityObserver(
    private val context: Context,
) : ConnectivityObserver {

    private val connectivityManager =
        context.getSystemService<ConnectivityManager>()!!

    override val connectivityState: Flow<ConnectivityObserver.Status>
        get() = callbackFlow {
            fun sendCurrentState() {
                val airplaneMode = isAirplaneModeOn()
                val activeNetwork = connectivityManager.activeNetwork
                val capabilities =
                    connectivityManager.getNetworkCapabilities(activeNetwork)
                val isConnected =
                    capabilities?.hasCapability(
                        NetworkCapabilities.NET_CAPABILITY_VALIDATED
                    ) == true
                val status = when {
                    airplaneMode -> ConnectivityObserver.Status.AirplaneMode
                    isConnected -> ConnectivityObserver.Status.Available
                    else -> ConnectivityObserver.Status.Lost
                }
                trySend(status)
            }

            val networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onCapabilitiesChanged(
                    network: Network,
                    networkCapabilities: NetworkCapabilities
                ) {
                    sendCurrentState()
                }

                override fun onAvailable(network: Network) {
                    sendCurrentState()
                }

                override fun onLost(network: Network) {
                    sendCurrentState()
                }
            }

            val airplaneModeReceiver = object : BroadcastReceiver() {
                override fun onReceive(p0: Context?, p1: Intent?) {
                    sendCurrentState()
                }
            }

            connectivityManager.registerDefaultNetworkCallback(networkCallback)

            val filter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            context.registerReceiver(airplaneModeReceiver, filter)

            sendCurrentState()

            awaitClose {
                connectivityManager.unregisterNetworkCallback(networkCallback)
                context.unregisterReceiver(airplaneModeReceiver)
            }
        }

    private fun isAirplaneModeOn(): Boolean {
        return android.provider.Settings.Global.getInt(
            context.contentResolver,
            android.provider.Settings.Global.AIRPLANE_MODE_ON,
            0
        ) != 0
    }
}
