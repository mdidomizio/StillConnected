package com.example.stillconnected

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stillconnected.ui.HomeScreen
import com.example.stillconnected.ui.HomeViewModel
import com.example.stillconnected.ui.theme.StillConnectedTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<HomeViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return HomeViewModel(
                        AndroidConnectivityObserver(applicationContext)
                    ) as T
                }
            }
        }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StillConnectedTheme {
                HomeScreen(viewModel = viewModel)
            }
        }
    }
}
