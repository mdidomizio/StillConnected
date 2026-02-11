package com.example.stillconnected

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.stillconnected.ui.HomeScreen
import com.example.stillconnected.ui.theme.StillConnectedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StillConnectedTheme {
                HomeScreen(/*modifier = Modifier.fillMaxSize()*/)
            }
        }
    }
}
